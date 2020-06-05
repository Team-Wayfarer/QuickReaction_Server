package org.quickresponse.qr.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.user.User;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String contact;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .contact(contact)
                .email(email)
                .password(password)
                .build();
    }
}
