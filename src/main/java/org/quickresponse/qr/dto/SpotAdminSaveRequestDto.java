package org.quickresponse.qr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.SpotAdmin;

@Getter
@NoArgsConstructor
public class SpotAdminSaveRequestDto {

    private String name;
    private String businessNumber;

    @Builder
    public SpotAdminSaveRequestDto(String name, String businessNumber) {
        this.name = name;
        this.businessNumber = businessNumber;
    }

    public SpotAdmin toEntity(){
      return SpotAdmin.builder()
                .name(name)
                .businessNumber(businessNumber)
                .build();
    }
}
