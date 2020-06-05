package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.dto.DefaultResponse;
import org.quickresponse.qr.dto.TokenResponse;
import org.quickresponse.qr.dto.UserLoginDto;
import org.quickresponse.qr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/users")
public class LoginUserController {

    private final UserService userService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserLoginDto dto) {
        String token = userService.createToken(dto);
        return new TokenResponse(token, "bearer");
    }
}
