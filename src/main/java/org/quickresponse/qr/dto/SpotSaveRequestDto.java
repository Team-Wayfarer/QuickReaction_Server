package org.quickresponse.qr.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Address;
import org.quickresponse.qr.domain.Spot;


@Getter
@NoArgsConstructor
public class SpotSaveRequestDto {

    private String name;
    private Address address;
    private String lat;
    private String lng;

    public Spot toEntity(){
        return Spot.builder()
                .name(name)
                .address(address)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
