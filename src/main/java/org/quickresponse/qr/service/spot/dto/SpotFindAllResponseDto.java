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
public class SpotFindAllResponseDto {

    private Long id;
    private String name;
    private Address address;
    private String lat;
    private String lng;

    public SpotFindAllResponseDto(Spot spot) {
        id = spot.getId();
        name = spot.getName();
        address = spot.getAddress();
        lat = spot.getLat();
        lng = spot.getLng();
    }
}
