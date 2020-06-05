package org.quickresponse.qr.service.user.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
