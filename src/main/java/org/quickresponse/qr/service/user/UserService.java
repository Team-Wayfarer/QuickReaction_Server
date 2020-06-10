package org.quickresponse.qr.service.user;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserRepository;
import org.quickresponse.qr.domain.user.UserStatus;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.quickresponse.qr.domain.visitInfo.VisitInfoRepository;
import org.quickresponse.qr.exception.UserException;
import org.quickresponse.qr.service.common.dto.ErrorCode;
import org.quickresponse.qr.service.user.dto.UserDetailResponseDto;
import org.quickresponse.qr.service.user.dto.UserLoginDto;
import org.quickresponse.qr.service.user.dto.UserSaveRequestDto;
import org.quickresponse.qr.service.user.dto.UserUpdateRequestDto;
import org.quickresponse.qr.util.JwtTokenProvider;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VisitInfoRepository visitInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;


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

    public User updateContact(Long id, UserUpdateRequestDto dto) {
        userRepository.updateContact(id, dto.getContact());
        return userRepository.findById(id);
    }

    public Long getUserId(String email) {
        return this.getUser(email).getId();
    }

    @Transactional
    public User changeUserStatus(Long userId, UserStatus userStatus) {
        User user = userRepository.findById(userId);
        if (user == null)
            throw new IllegalStateException("등록된 사용자가 없습니다.");
        user.setUserStatus(userStatus);

        List<VisitInfo> visitInfoList = visitInfoRepository.findVisitInfoListByUserId(user.getId(), 0, 100);

        for (VisitInfo VisitInfoByUser : visitInfoList) {
            if (!isOnAWeek(VisitInfoByUser.getLocalDateTime(), LocalDateTime.now())) {
                continue;
            }

            List<VisitInfo> visitInfoListBySpotId = visitInfoRepository.findVisitInfoListBySpotId(VisitInfoByUser.getSpot().getId());

            for (VisitInfo VisitInfoBySpot : visitInfoListBySpotId) {
                if (!isBefore(VisitInfoByUser.getLocalDateTime(), VisitInfoBySpot.getLocalDateTime())) {
                    continue;
                }
                if (isOnThreeHours(VisitInfoByUser.getLocalDateTime(), VisitInfoBySpot.getLocalDateTime()) && isSameDay(VisitInfoByUser.getLocalDateTime(), VisitInfoBySpot.getLocalDateTime())) {
                    User doubtUser = userRepository.findById(VisitInfoBySpot.getUser().getId());
                    if (isInfection(doubtUser.getUserStatus())) {
                        continue;
                    }
                    doubtUser.setUserStatus(UserStatus.DOUBT);
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

    public boolean validatesEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user != null) {
            return true;
        }
        return false;
    }
}
