package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    @NotEmpty
    private String contact;
}
