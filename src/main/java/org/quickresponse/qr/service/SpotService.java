package org.quickresponse.qr.service;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.repository.SpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotService {

    private final SpotRepository spotRepository;

    @Transactional
    public Long join(SpotSaveRequestDto spotSaveRequestDto) {
        return spotRepository.join(spotSaveRequestDto.toEntity());
    }
}
