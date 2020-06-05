package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.service.code.CodeService;
import org.quickresponse.qr.service.code.dto.SpotResponseDtoByCode;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = { "5. Code" })
@Slf4j
@RestController
@RequestMapping("/cfcqr/api/codes")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @ApiOperation(value = "QR코드 고유번호", notes=" QR코드의 고유번호를 반환합니다. ")
    @GetMapping("/get/{id}")
    public SpotResponseDtoByCode findSpotDetails(@PathVariable("id") Long id) {
        return codeService.findById(id);
    }

    @ApiOperation(value = "QR코드 생성 및 저장", notes=" 장소 고유번호로 찾은 장소로 QR코드를 생성하여, 장소에 저장합니다. ")
    @PostMapping("/provide/{spot_id}")
    public Long saveCode(@PathVariable("spot_id") Long spotId) throws Exception {
        return codeService.save(spotId).getId();
    }

    @ApiOperation(value = "QR코드 이미지가 저장된 URL", notes=" QR코드 이미지가 저장된 URL을 반환합니다. ")
    @GetMapping("/{id}")
    public String getQrImage(@PathVariable("id") Long id) throws IOException {
        return codeService.getUrl(id);
    }
}
