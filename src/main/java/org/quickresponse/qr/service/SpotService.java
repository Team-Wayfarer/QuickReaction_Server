package org.quickresponse.qr.service;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.repository.SpotAdminRepository;
import org.quickresponse.qr.repository.SpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotAdminRepository spotAdminRepository;

    @Transactional
    public Long join(SpotSaveRequestDto spotSaveRequestDto, Long spotAdminId) {
        Spot spot = spotSaveRequestDto.toEntity();
        spot.setSpotAdmin(spotAdminRepository.find(spotAdminId));
        return spotRepository.join(spot);
    }
}
