package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.service.common.dto.TokenResponse;
import org.quickresponse.qr.service.spotAdmin.SpotAdminService;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminLoginDto;
import org.quickresponse.qr.service.user.dto.UserLoginDto;
import org.quickresponse.qr.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = { "1. Login" })
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/login")
public class LoginController {

    private final UserService userService;
    private final SpotAdminService spotAdminService;

    @ApiOperation(value = "일반 유저 로그인", notes = "유저 로그인. 성공시 jwt 토큰을 반홥합니다.")
    @PostMapping("/user")
    public TokenResponse login(@RequestBody UserLoginDto dto) {
        String token = userService.createToken(dto);
        Long userId = userService.getUserId(dto.getEmail());
        return new TokenResponse(token, "bearer", userId);
    }

    @ApiOperation(value = "점주 로그인", notes = " 점주 로그인. 성공시 jwt 토큰을 반홥합니다.")
    @PostMapping("/spotAdmin")
    public TokenResponse loginSpotAdmin(@RequestBody SpotAdminLoginDto spotAdminLoginDto){
        String token = spotAdminService.createToken(spotAdminLoginDto);
        Long spotAdminId = spotAdminService.getSpotAdminId(spotAdminLoginDto.getEmail());
        return new TokenResponse(token, "bearer", spotAdminId);
    }
}
