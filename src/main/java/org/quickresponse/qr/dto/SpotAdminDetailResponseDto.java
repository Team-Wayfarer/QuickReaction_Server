package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.SpotAdmin;

@Getter
@NoArgsConstructor
public class SpotAdminDetailResponseDto {
    private String SpotAdminName;
    private String businessNumber;
    private String contact;
    private String SpotName;

    public SpotAdminDetailResponseDto(SpotAdmin spotAdmin) {
        SpotAdminName = spotAdmin.getName() ;
        businessNumber = spotAdmin.getBusinessNumber();
        SpotName = spotAdmin.getSpot().getName();
        contact = spotAdmin.getContact();
    }
}
