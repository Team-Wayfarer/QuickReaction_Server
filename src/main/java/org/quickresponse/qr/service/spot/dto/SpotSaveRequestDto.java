package org.quickresponse.qr.service.spot.dto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Address;
import org.quickresponse.qr.domain.spot.Spot;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotSaveRequestDto {

    private String name;
    private Address address;
    private String lat;
    private String lng;

    @Builder
    public SpotSaveRequestDto(String name, Address address, String lat, String lng) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public Spot toEntity(){
        return Spot.builder()
                .name(name)
                .address(address)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
