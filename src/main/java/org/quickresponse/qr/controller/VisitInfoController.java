package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.quickresponse.qr.service.visitInfo.dto.VisitInfoListByUserIdDto;
import org.quickresponse.qr.service.visitInfo.dto.VisitInfoSaveResponseDto;
import org.quickresponse.qr.service.visitInfo.VisitInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = { "6. VisitInfo" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/visitInfos")
public class VisitInfoController {

    private final VisitInfoService visitInfoService;

    @ApiOperation(value = "방문 정보 저장", notes = "장소 방문 정보를 저장합니다.")
    @PostMapping("/{userId}/{spotId}")
    public VisitInfoSaveResponseDto save(@PathVariable("userId") Long userId, @PathVariable("spotId") Long spotId){
        Long visitInfoId = visitInfoService.save(userId, spotId);
        return new VisitInfoSaveResponseDto(visitInfoId);
    }

    @ApiOperation(value = "유저 방문지 리스트 조회", notes = ""+
            "SpotDetailByVisitInfo spot; 장소 정보 \n" +
            "String name; 장소 이름\n" +
            "Address address; 주소 \n" +
            "String lat; 위도\n" +
            "String lng; 경도 \n "+
            "LocalDateTime localDateTime; 유저의 장소 방문 시간 \n" )
    @GetMapping("/{userId}")
    public List<VisitInfoListByUserIdDto> findVisitInfoByUserId(@PathVariable("userId") Long id,
                                                        @RequestParam(value="offset", defaultValue = "0") int offset,
                                                        @RequestParam(value="limit", defaultValue = "100") int limit){
        List<VisitInfoListByUserIdDto> visitInfoListByUserId = visitInfoService.findVisitInfoListByUserId(id, offset, limit);
        return visitInfoListByUserId;
    }
}
