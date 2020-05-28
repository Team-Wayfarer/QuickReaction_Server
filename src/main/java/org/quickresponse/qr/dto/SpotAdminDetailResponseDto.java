package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.SpotAdmin;

@Getter
@NoArgsConstructor
public class SpotAdminDetailResponseDto {
    private String SpotAdminname;
    private String businessNumber;
    private String SpotName;

    public SpotAdminDetailResponseDto(SpotAdmin spotAdmin) {
        SpotAdminname = spotAdmin.getName() ;
        businessNumber = spotAdmin.getBusinessNumber();
        SpotName = spotAdmin.getSpot().getName();
    }
}
