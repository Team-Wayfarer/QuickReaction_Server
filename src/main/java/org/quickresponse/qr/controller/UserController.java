package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.service.user.dto.UserDetailResponseDto;
import org.quickresponse.qr.service.user.dto.UserSaveRequestDto;
import org.quickresponse.qr.service.user.dto.UserUpdateRequestDto;
import org.quickresponse.qr.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. User"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "일반 유저 생성", notes = "일반 유저를 생성합니다. Long id를 반홥합니다.")
    @PostMapping
    public Long join(@RequestBody UserSaveRequestDto dto) {
        return userService.join(dto);
    }

    @ApiOperation(value = "유저 상세 정보 조회", notes = "" +
            "Long id; 유저 고유번호\n" +
            "String name; 유저 이름 \n" +
            "String contact; 유저 전화번호\n" +
            "String email; 유저 이메일")
    @GetMapping("/{id}")
    public UserDetailResponseDto getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    @ApiOperation(value = "유저 전화번호 변경", notes = "유저의 전화번호를 변경합니다.")
    @PutMapping("/{id}")
    public Long updateContact(@PathVariable Long id, @RequestBody UserUpdateRequestDto dto) {
        User user = userService.updateContact(id, dto);
        return user.getId();
    }

    @ApiOperation(value = "유저 이메일 중복 확인", notes = "RequestBody로 이메일 전달" )
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email) {
        return ResponseEntity.ok(userService.validatesEmail(email));
    }
}
