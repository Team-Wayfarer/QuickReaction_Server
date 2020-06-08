package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.service.spotAdmin.SpotAdminService;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminDetailResponseDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminFindAllResponseDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminSaveRequestDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminSaveResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = { "3. SpotAdmin" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/spotAdmins")
public class SpotAdminController {

    private final SpotAdminService spotAdminService;

    @ApiOperation(value = "점주 생성", notes="점주를 생성합니다.")
    @PostMapping("/")
    public SpotAdminSaveResponseDto join(@RequestBody SpotAdminSaveRequestDto spotAdminSaveRequestDto){
        Long id = spotAdminService.join(spotAdminSaveRequestDto);
        return new SpotAdminSaveResponseDto(id);
    }

    @ApiOperation(value = "전체 점주 조회", notes="" +
            "String SpotAdminName; 점주 이름\n" +
            "String email; 점주 이메일 \n" +
            "String businessNumber; 사업자 번호\n" +
            "String contact; 점주 전화번호\n" +
            "String SpotName; 장소 이름")
    @GetMapping("/")
    public List<SpotAdminFindAllResponseDto> findAllSpotAdmin(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                              @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<SpotAdmin> all = spotAdminService.findAll(offset, limit);
        List<SpotAdminFindAllResponseDto> collect = all.stream()
                .map(s -> new SpotAdminFindAllResponseDto(s))
                .collect(Collectors.toList());
        return collect;
    }

    @ApiOperation(value = "점주 상세 정보 조회", notes="" +
            "String SpotAdminName; 점주 이름\n" +
            "String email; 점주 이메일 \n" +
            "String businessNumber; 사업자 번호\n" +
            "String contact; 점주 전화번호\n" +
            "String SpotName; 장소 이름")
    @GetMapping("/{spotAdminId}")
    public SpotAdminDetailResponseDto findOneDetail(@PathVariable("spotAdminId") Long id){
        return new SpotAdminDetailResponseDto(spotAdminService.findOneDetail(id));
    }

    @ApiOperation(value = "관리자 이메일 중복 확인", notes = "RequestBody로 이메일 전달" )
    @PostMapping("/checkmail")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email) {
        return ResponseEntity.ok(spotAdminService.validatesEmail(email));
    }
    @ApiOperation(value = "사업자번호 중복 확인", notes = "RequestBody로 번호 전달" )
    @PostMapping("/checknumber")
    public ResponseEntity<Boolean> checkBusinessNumber(@RequestBody String businessNumber) {
        return ResponseEntity.ok(spotAdminService.validatesBusinessNumber(businessNumber));
    }
}
