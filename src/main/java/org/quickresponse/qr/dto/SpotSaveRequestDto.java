package org.quickresponse.qr.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class SpotSaveRequestDto {
    private String name;
    private String address;
    private String lat;
    private String lng;

    public SpotSaveRequestDto(String name, String address, String lat, String lng) {
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
