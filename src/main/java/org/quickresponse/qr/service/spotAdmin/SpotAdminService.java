package org.quickresponse.qr.service.spotAdmin;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.domain.spotAdmin.SpotAdminRepository;
import org.quickresponse.qr.exception.SpotAdminException;
import org.quickresponse.qr.service.common.dto.ErrorCode;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminLoginDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminSaveRequestDto;
import org.quickresponse.qr.util.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotAdminService {

    private final SpotAdminRepository spotAdminRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long join(SpotAdminSaveRequestDto spotAdminSaveRequestDto) {
        SpotAdmin spotAdmin = spotAdminRepository.join(spotAdminSaveRequestDto.toEntity());
        return spotAdmin.getId();
    }

    public SpotAdmin findOneDetail(Long id) {
        return spotAdminRepository.findOneDetail(id);
    }

    public List<SpotAdmin> findAll(int offset, int limit) {
        return spotAdminRepository.findAll(offset, limit);
    }

    @Transactional
    public String createToken(SpotAdminLoginDto spotAdminLoginDto) {
        String email = spotAdminLoginDto.getEmail();
        SpotAdmin spotAdmin = spotAdminRepository.findSpotAdminByEmail(email)
                .orElseThrow(() -> new SpotAdminException(String.format("%s: 가입되지 않은 이메일입니다.", email), ErrorCode.UNSIGNED));

        if (!spotAdmin.checkPassword(spotAdminLoginDto.getPassword())) {
            throw new SpotAdminException("틀린 암호입니다.", ErrorCode.WRONG_PASSWORD);
        }
        spotAdmin.setDuid(spotAdminLoginDto.getDuid());

        return jwtTokenProvider.createToken(spotAdminLoginDto.getEmail());
    }

    public Long getSpotAdminId(String email) {
        return spotAdminRepository.findSpotAdminByEmail(email).get().getId();
    }

    public boolean validatesEmail(String email) {
        SpotAdmin spotAdmin = spotAdminRepository.findSpotAdminByEmail(email)
                .orElse(null);
        if(spotAdmin != null) {
            return true;
        }
        return false;
    }

    public boolean validatesBusinessNumber(String businessNumber) {
        SpotAdmin spotAdmin = spotAdminRepository.findByBusinessNumber(businessNumber)
                .orElse(null);
        if(spotAdmin != null) {
            return true;
        }
        return false;
    }
}
