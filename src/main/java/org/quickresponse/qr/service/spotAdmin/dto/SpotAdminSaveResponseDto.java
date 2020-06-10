package org.quickresponse.qr.service.spotAdmin.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotAdminSaveResponseDto {

    private Long spotAdmin_id;

    public SpotAdminSaveResponseDto(Long spotAdmin_id) {
        this.spotAdmin_id = spotAdmin_id;
    }
}
