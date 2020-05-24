package org.quickresponse.qr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.User;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserLoginDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String contact;

    @Builder
    public UserLoginDto(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .contact(this.contact)
                .build();
    }
}
