package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserStatus;
import org.quickresponse.qr.exception.UserException;
import org.quickresponse.qr.service.common.dto.ErrorCode;
import org.quickresponse.qr.service.user.dto.*;
import org.quickresponse.qr.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
            "String email; 유저 이메일\n" +
            "UserStatus userStatus; 유저 상태 정보 \n" +
            "LocalDateTime whenChange; 상태 변경 시간 ")
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

    @ApiOperation(value = "유저 확진자 등록", notes = "선택한 유저를 확진자로 등록합니다")
    @PostMapping("/{userId}/change/{status}/infect")
    public ChangeInfectionStatusResponseDto changeUserInfectionStatus(@PathVariable("userId") Long userId, @RequestParam("status") UserStatus userStatus) throws UnsupportedEncodingException {
        if (userStatus != UserStatus.INFECT)
            throw new UserException("확진자 등록만 가능합니다.", ErrorCode.FAVORITE_DUPLICATED.INVALID_AUTHENTICATION);

        User user = userService.changeUserInfectionStatus(userId, userStatus);
        return new ChangeInfectionStatusResponseDto(user);
    }

    @ApiOperation(value = "유저 이메일 중복 확인", notes = "RequestBody로 이메일 전달" )
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email) {
        return ResponseEntity.ok(userService.validatesEmail(email));
    }

    @ApiOperation(value = "유저 상태 변경", notes = "선택한 유저의 상태를 변경합니다.")
    @PostMapping("/{userId}/change/{status}")
    public ChangeStatusResponseDto changeUserStatus(@PathVariable("userId") Long userId, @RequestParam("status") UserStatus userStatus) {
        if (userStatus == UserStatus.INFECT)
            throw new UserException("현재 접근에서 확진자 등록은 불가능 합니다. ", ErrorCode.INVALID_AUTHENTICATION);

        User user = userService.changeUserStatus(userId, userStatus);
        return new ChangeStatusResponseDto(user);
    }
}
