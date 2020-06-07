package org.quickresponse.qr.service.user;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.exception.UserException;
import org.quickresponse.qr.domain.user.UserRepository;
import org.quickresponse.qr.service.common.dto.*;
import org.quickresponse.qr.service.user.dto.UserDetailResponseDto;
import org.quickresponse.qr.service.user.dto.UserLoginDto;
import org.quickresponse.qr.service.user.dto.UserSaveRequestDto;
import org.quickresponse.qr.service.user.dto.UserUpdateRequestDto;
import org.quickresponse.qr.util.JwtTokenProvider;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean validatesEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user != null) {
            return true;
        }
        return false;
    }
}
