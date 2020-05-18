package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Spot;

import java.util.List;

@Getter
@NoArgsConstructor
public class SpotFindAllRequestDto {
    private Long id;
    private String name;
    private String address;
    private String lat;
    private String lng;
    private List<SpotVisitInfoDetailsDto> spotVisitInfoDetailsDto;

    public SpotFindAllRequestDto(Spot spot) {
        id = spot.getId();
        name = spot.getName();
        address = spot.getAddress();
        lat = spot.getLat();
        lng = spot.getLng();
        spot.getVisitInfoList().stream()
                .map()
    }
}
