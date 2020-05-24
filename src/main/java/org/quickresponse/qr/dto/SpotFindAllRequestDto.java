package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Address;
import org.quickresponse.qr.domain.Spot;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SpotFindAllRequestDto {

    private Long id;
    private String name;
    private Address address;
    private String lat;
    private String lng;

    public SpotFindAllRequestDto(Spot spot) {
        id = spot.getId();
        name = spot.getName();
        address = spot.getAddress();
        lat = spot.getLat();
        lng = spot.getLng();
    }
}
