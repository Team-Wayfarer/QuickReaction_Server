package org.quickresponse.qr.service.spotAdmin.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotAdminLoginDto {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public SpotAdminLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
