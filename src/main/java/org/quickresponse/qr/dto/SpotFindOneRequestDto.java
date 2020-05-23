package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Spot;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SpotFindOneRequestDto {

    private Long id;
    private String name;
    private String address;
    private String lat;
    private String lng;
    private List<SpotVisitInfoDetailsDto> spotVisitInfoDetailsDto;

    public SpotFindOneRequestDto(Spot spot) {
        id = spot.getId();
        name = spot.getName();
        address = spot.getAddress();
        lat = spot.getLat();
        lng = spot.getLng();
        spotVisitInfoDetailsDto= spot.getVisitInfoList().stream()
                .map(v -> new SpotVisitInfoDetailsDto(v))
                .collect(Collectors.toList());
    }
}
