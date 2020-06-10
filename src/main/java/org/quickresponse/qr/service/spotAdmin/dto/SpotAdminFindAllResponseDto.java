package org.quickresponse.qr.service.spotAdmin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotAdminFindAllResponseDto {

    private String SpotAdminName;
    private String email;
    private String businessNumber;
    private String contact;
    private String SpotName;

    @Builder
    public SpotAdminFindAllResponseDto(SpotAdmin spotAdmin) {
        SpotAdminName = spotAdmin.getName();
        email = spotAdmin.getEmail();
        businessNumber = spotAdmin.getBusinessNumber();
        contact=spotAdmin.getContact();
        SpotName = spotAdmin.getSpot().getName();
    }
}

