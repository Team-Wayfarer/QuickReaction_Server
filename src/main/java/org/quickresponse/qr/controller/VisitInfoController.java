package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.quickresponse.qr.service.visitInfo.dto.VisitInfoListByUserIdDto;
import org.quickresponse.qr.service.visitInfo.dto.VisitInfoSaveResponseDto;
import org.quickresponse.qr.service.visitInfo.VisitInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/visitInfos")
public class VisitInfoController {

    private final VisitInfoService visitInfoService;

    @PostMapping("/{userId}/{spotId}")
    public VisitInfoSaveResponseDto save(@PathVariable("userId") Long userId, @PathVariable("spotId") Long spotId){
        Long visitInfoId = visitInfoService.save(userId, spotId);
        return new VisitInfoSaveResponseDto(visitInfoId);
    }

    @GetMapping("/{userId}")
    public List<VisitInfoListByUserIdDto> findVisitInfoByUserId(@PathVariable("userId") Long id,
                                                        @RequestParam(value="offset", defaultValue = "0") int offset,
                                                        @RequestParam(value="limit", defaultValue = "100") int limit){
        List<VisitInfoListByUserIdDto> visitInfoListByUserId = visitInfoService.findVisitInfoListByUserId(id, offset, limit);
        return visitInfoListByUserId;
    }


}
