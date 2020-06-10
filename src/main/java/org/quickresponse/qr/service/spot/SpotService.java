package org.quickresponse.qr.service.spot;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.service.spot.dto.SpotSaveRequestDto;
import org.quickresponse.qr.domain.spotAdmin.SpotAdminRepository;
import org.quickresponse.qr.domain.spot.SpotRepository;
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

        Spot joinedSpot = spotRepository.join(spot);
        return joinedSpot.getId();
    }

}
