package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpotSaveResponseDto {

    private Long spot_id;

    public SpotSaveResponseDto(Long id) {
        this.spot_id = id;
    }
}
