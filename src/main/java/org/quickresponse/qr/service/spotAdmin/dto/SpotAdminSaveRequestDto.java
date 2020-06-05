package org.quickresponse.qr.service.spotAdmin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotAdminSaveRequestDto {

    private String name;
    private String businessNumber;
    private String contact;

    @Builder
    public SpotAdminSaveRequestDto(String name, String businessNumber, String contact) {
        this.name = name;
        this.businessNumber = businessNumber;
        this.contact= contact;
    }

    public SpotAdmin toEntity(){
        return SpotAdmin.builder()
                .name(name)
                .businessNumber(businessNumber)
                .contact(contact)
                .build();
    }
}
