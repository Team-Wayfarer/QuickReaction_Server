package org.quickresponse.qr.service.visitInfo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Address;
import org.quickresponse.qr.domain.spot.Spot;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotDetailByVisitInfo {
    private String name;
    private Address address;
    private String lat;
    private String lng;

    public SpotDetailByVisitInfo(Spot spot) {
        name = spot.getName();
        address = spot.getAddress();
        lat = spot.getLat();
        lng = spot.getLng();
    }
}
