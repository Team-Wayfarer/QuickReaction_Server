package org.quickresponse.qr.service.user;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserRepository;
import org.quickresponse.qr.domain.user.UserStatus;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.quickresponse.qr.domain.visitInfo.VisitInfoRepository;
import org.quickresponse.qr.exception.UserException;
import org.quickresponse.qr.service.common.dto.ErrorCode;
import org.quickresponse.qr.service.fcm.AndroidPushNotificationsService;
import org.quickresponse.qr.service.fcm.AndroidPushPeriodicNotifications;
import org.quickresponse.qr.service.user.dto.UserDetailResponseDto;
import org.quickresponse.qr.service.user.dto.UserLoginDto;
import org.quickresponse.qr.service.user.dto.UserSaveRequestDto;
import org.quickresponse.qr.service.user.dto.UserUpdateRequestDto;
import org.quickresponse.qr.util.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VisitInfoRepository visitInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired AndroidPushNotificationsService androidPushNotificationsService;

    @Transactional
    public Long join(UserSaveRequestDto dto) {
        validateEmail(dto.toEntity());
        try {
            return userRepository.save(dto.toEntity()).getId();
        } catch (Exception e) {
            throw new DuplicateKeyException(dto.getEmail());
        }
    }

    private void validateEmail(final User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException(user.getEmail());
        }
    }

    @Transactional
    public String createToken(UserLoginDto dto) {
        String email = dto.getEmail();
        User user = getUser(email);
        if (!user.checkPassword(dto.getPassword())) {
            throw new UserException("틀린 암호입니다", ErrorCode.WRONG_PASSWORD);
        }
        user.setDuid(dto.getDuid());
        return jwtTokenProvider.createToken(dto.getEmail());

    }

    private User getUser(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(String.format("%s: 가입되지 않은 이메일입니다.", email), ErrorCode.UNSIGNED));
    }

    public long login(UserLoginDto dto) {
        return userRepository.login(dto);
    }

    public UserDetailResponseDto getUserInfo(Long id) {
        return new UserDetailResponseDto(userRepository.findById(id));
    }

    @Transactional
    public User updateContact(Long id, UserUpdateRequestDto dto) {
        userRepository.updateContact(id, dto.getContact());
        return userRepository.findById(id);
    }

    public Long getUserId(String email) {
        return this.getUser(email).getId();
    }

    @Transactional
    public User changeUserStatus(Long userId, UserStatus userStatus) throws UnsupportedEncodingException {
        User user = userRepository.findById(userId);
        if (user == null)
            throw new IllegalStateException("등록된 사용자가 없습니다.");
        user.setUserStatus(userStatus);

        List<VisitInfo> visitInfoListByInfection = visitInfoRepository.findVisitInfoListByUserId(user.getId(), 0, 100);

        for (VisitInfo infectionVisitInfo : visitInfoListByInfection) {
            if (!isOnAWeek(infectionVisitInfo.getLocalDateTime(), LocalDateTime.now())) {
                continue;
            }

            List<VisitInfo> allUserVisitInfoBySpot = visitInfoRepository.findVisitInfoListBySpotId(infectionVisitInfo.getSpot().getId());

            for (VisitInfo userVisitInfoBySpot : allUserVisitInfoBySpot) {
                if (!isBefore(infectionVisitInfo.getLocalDateTime(), userVisitInfoBySpot.getLocalDateTime())) {
                    continue;
                }
                if (isOnThreeHours(infectionVisitInfo.getLocalDateTime(), userVisitInfoBySpot.getLocalDateTime()) && isSameDay(infectionVisitInfo.getLocalDateTime(), userVisitInfoBySpot.getLocalDateTime())) {
                    User doubtUser = userRepository.findById(userVisitInfoBySpot.getUser().getId());
                    if (isInfection(doubtUser.getUserStatus())) {
                        continue;
                    }
                    doubtUser.setUserStatus(UserStatus.DOUBT);

                    LocalDateTime infectionVisitTime = infectionVisitInfo.getLocalDateTime();
                    LocalDateTime userVisitTime = userVisitInfoBySpot.getLocalDateTime();
                    Spot userVisitedSpot = userVisitInfoBySpot.getSpot();
                    String duid = doubtUser.getDuid();
                    if(duid==null){
                        continue;
                    }

                    String notifications = AndroidPushPeriodicNotifications.DoubtUserNotification(duid,userVisitedSpot, infectionVisitTime,userVisitTime );
                    HttpEntity<String> request = new HttpEntity<>(notifications);
                    CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
                    CompletableFuture.allOf(pushNotification).join();
                }
            }
        }

        return user;
    }

    private boolean isInfection(UserStatus userStatus) {
        if (userStatus != UserStatus.INFECT) {
            return false;
        }
        return true;
    }


    private boolean isOnAWeek(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (ChronoUnit.DAYS.between(startDateTime, endDateTime) > 7) {
            return false;
        }

        return true;
    }

    private boolean isBefore(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (!startDateTime.isBefore(endDateTime)) {
            return false;
        }

        return true;
    }

    private boolean isOnThreeHours(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (ChronoUnit.HOURS.between(startDateTime, endDateTime) > 3) {
            return false;
        }

        return true;
    }

    private boolean isSameDay(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (ChronoUnit.DAYS.between(startDateTime, endDateTime) != 0) {
            return false;
        }

        return true;
    }

    public boolean validatesEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user != null) {
            return true;
        }
        return false;
    }
}
