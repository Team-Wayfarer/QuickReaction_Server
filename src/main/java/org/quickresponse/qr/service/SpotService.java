package org.quickresponse.qr.service;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.domain.SpotAdmin;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.repository.SpotAdminRepository;
import org.quickresponse.qr.repository.SpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotAdminRepository spotAdminRepository;

    @Transactional
    public Long join(SpotSaveRequestDto spotSaveRequestDto, Long spotAdminId) {
        SpotAdmin findedSpotAdmin = spotAdminRepository.find(spotAdminId);
        if(findedSpotAdmin==null) {
            throw new NullPointerException("등록된 사업자가 아닙니다.");
        }

        Spot spotBySpotAdminId = spotRepository.findSpotBySpotAdminId(spotAdminId);
        if(spotBySpotAdminId!=null) {
            throw new IllegalStateException("이미 spot이 등록되어있는 사업자 입니다.");
        }

        Spot spot = spotSaveRequestDto.toEntity();
        spot.setSpotAdmin(findedSpotAdmin);

        return spotRepository.join(spot);
    }
}
