package org.quickresponse.qr.service.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Address;
import org.quickresponse.qr.domain.spot.Spot;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotFindOneResponseDto {

    private Long id;
    private String name;
    private Address address;
    private String lat;
    private String lng;
    private String spotAdminContact;
    private List<SpotVisitInfoDetailsDto> spotVisitInfoDetailsDto;

    public SpotFindOneResponseDto(Spot spot) {
        id = spot.getId();
        name = spot.getName();
        address = spot.getAddress();
        lat = spot.getLat();
        lng = spot.getLng();
        spotAdminContact=spot.getSpotAdmin().getContact();
        spotVisitInfoDetailsDto= spot.getVisitInfoList().stream()
                .map(v -> new SpotVisitInfoDetailsDto(v))
                .collect(Collectors.toList());
    }
}
