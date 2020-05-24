package org.quickresponse.qr.service;

import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.domain.VisitInfo;
import org.quickresponse.qr.dto.UserLoginDto;
import org.quickresponse.qr.dto.UserSaveRequestDto;
import org.quickresponse.qr.dto.UserUpdateRequestDto;
import org.quickresponse.qr.dto.VisitInfoDto;
import org.quickresponse.qr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User join(UserSaveRequestDto dto) {
        User user = dto.toEntity();
        userRepository.save(user);
        return user;
    }

    public boolean login(UserLoginDto dto) {
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
