package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.service.code.CodeService;
import org.quickresponse.qr.service.code.dto.SpotResponseDtoByCode;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/cfcqr/api/codes")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/get/{id}")
    public SpotResponseDtoByCode findSpotDetails(@PathVariable("id") Long id) {
        return codeService.findById(id);
    }

    @PostMapping("/provide/{spot_id}")
    public Long saveCode(@PathVariable("spot_id") Long spotId) throws Exception {
        return codeService.save(spotId).getId();
    }

    @GetMapping("/{id}")
    public String getQrImage(@PathVariable("id") Long id) throws IOException {
        return codeService.getUrl(id);
//        System.out.println(url);
//        File sourceimage = new File(url);
//        return ImageIO.read(sourceimage);
    }
}
