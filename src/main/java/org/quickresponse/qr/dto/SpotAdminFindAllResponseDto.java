package org.quickresponse.qr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.SpotAdmin;

@Getter
@NoArgsConstructor
public class SpotAdminFindAllResponseDto {
    private String SpotAdminName;
    private String businessNumber;
    private String contact;
    private String SpotName;

    @Builder
    public SpotAdminFindAllResponseDto(SpotAdmin spotAdmin) {
        SpotAdminName = spotAdmin.getName();
        businessNumber = spotAdmin.getBusinessNumber();
        contact=spotAdmin.getContact();
        SpotName = spotAdmin.getSpot().getName();
    }
}
