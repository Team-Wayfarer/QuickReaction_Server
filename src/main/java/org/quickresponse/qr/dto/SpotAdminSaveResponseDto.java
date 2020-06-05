package org.quickresponse.qr.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpotAdminSaveResponseDto {

    private Long spotAdmin_id;

    public SpotAdminSaveResponseDto(Long spotAdmin_id) {
        this.spotAdmin_id = spotAdmin_id;
    }
}
