package org.quickresponse.qr.service;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.domain.VisitInfo;
import org.quickresponse.qr.dto.*;
import org.quickresponse.qr.exception.UserException;
import org.quickresponse.qr.repository.UserRepository;
import org.quickresponse.qr.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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
        if( userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException(user.getEmail());
        }
    }

    @Transactional
    public String createToken(UserLoginDto dto) {
        String email = dto.getEmail();
        User user = getUser(email);
        if( !user.checkPassword(dto.getPassword())) {
            throw new UserException("",ErrorCode.WRONG_PASSWORD);
        }
        return jwtTokenProvider.createToken(dto.getEmail());

    }

    private User getUser(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UserException(String.format("%s: 가입되지 않은 이메일입니다.", email), ErrorCode.UNSIGNED));
    }

    public long login(UserLoginDto dto) {
        return userRepository.login(dto);
    }

    public User getUserInfo(Long id) {
        return userRepository.findById(id);
    }

    public List<VisitInfoDto> getVisitInfoList(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            List<VisitInfoDto> res = new ArrayList<>();
            for (VisitInfo visitInfo : user.getVisitInfoList()) {
                VisitInfoDto dto = VisitInfoDto.builder()
                        .spot(visitInfo.getSpot())
                        .localDateTime(visitInfo.getLocalDateTime())
                        .build();
                res.add(dto);
            }
            return Collections.unmodifiableList(res);
        }
        return null;
    }

    public User updateContact(Long id, UserUpdateRequestDto dto) {
        userRepository.updateContact(id, dto.getContact());
        return userRepository.findById(id);
    }
}
