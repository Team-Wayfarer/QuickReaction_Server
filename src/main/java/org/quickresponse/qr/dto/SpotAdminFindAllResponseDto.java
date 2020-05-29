package org.quickresponse.qr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.SpotAdmin;

@Getter
@NoArgsConstructor
public class SpotAdminFindAllResponseDto {
    private String SpotAdminname;
    private String businessNumber;
    private String SpotName;

    @Builder
    public SpotAdminFindAllResponseDto(SpotAdmin spotAdmin) {
        SpotAdminname = spotAdmin.getName() ;
        businessNumber = spotAdmin.getBusinessNumber();
        SpotName = spotAdmin.getSpot().getName();
    }
}
