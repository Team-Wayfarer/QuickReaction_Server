package org.quickresponse.qr.service.user.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    @NotEmpty
    private String contact;
}
