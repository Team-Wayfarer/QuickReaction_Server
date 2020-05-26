package org.quickresponse.qr.service;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.dto.SpotAdminSaveRequestDto;
import org.quickresponse.qr.repository.SpotAdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotAdminService {

    private final SpotAdminRepository spotAdminRepository;

    @Transactional
    public Long join(SpotAdminSaveRequestDto spotAdminSaveRequestDto) {

        return spotAdminRepository.join(spotAdminSaveRequestDto.toEntity());
    }
}
