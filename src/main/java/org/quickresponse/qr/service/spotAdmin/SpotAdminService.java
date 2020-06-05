package org.quickresponse.qr.service.spotAdmin;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.domain.spotAdmin.SpotAdminRepository;
import org.quickresponse.qr.service.spot.SpotService;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotAdminService {

    private final SpotAdminRepository spotAdminRepository;

    @Transactional
    public Long join(SpotAdminSaveRequestDto spotAdminSaveRequestDto) {
        return spotAdminRepository.join(spotAdminSaveRequestDto.toEntity());
    }

    public SpotAdmin findOneDetail(Long id) {
        return spotAdminRepository.findOneDetail(id);
    }

    public List<SpotAdmin> findAll(int offset, int limit) {
        return spotAdminRepository.findAll(offset, limit);
    }
}
