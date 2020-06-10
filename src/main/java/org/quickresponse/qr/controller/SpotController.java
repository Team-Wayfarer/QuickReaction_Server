package org.quickresponse.qr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.service.spot.dto.SpotFindAllResponseDto;
import org.quickresponse.qr.service.spot.dto.SpotFindOneResponseDto;
import org.quickresponse.qr.service.spot.dto.SpotSaveRequestDto;
import org.quickresponse.qr.service.spot.dto.SpotSaveResponseDto;
import org.quickresponse.qr.domain.spot.SpotRepository;
import org.quickresponse.qr.service.spot.SpotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = { "4. Spot" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/spots")
public class SpotController {

    private final SpotService spotService;
    private final SpotRepository spotRepository;

    @ApiOperation(value = "장소 등록", notes = "장소를 등록합니다.")
    @PostMapping("/{spotAdminId}")
    public SpotSaveResponseDto save(@PathVariable("spotAdminId") Long spotAdminId,
                                    @RequestBody SpotSaveRequestDto spotSaveRequestDto){
        Long joinId = spotService.join(spotSaveRequestDto, spotAdminId);
        return new SpotSaveResponseDto(joinId);
    }

    @ApiOperation(value = "전체 장소 조회", notes = ""+
            "Long id; 장소 고유 번호\n" +
            "String name; 장소 이름\n" +
            "Address address; 주소 \n" +
            "String lat; 위도\n " +
            "String lng 경도;")
    @GetMapping("/")                                                    //모든 spot 확인
    public List<SpotFindAllResponseDto> findAllSpot(){
        List<Spot> spots = spotRepository.findAll();
        if(spots.isEmpty()) {
            throw new IllegalStateException("현재 등록된 장소가 없습니다. ");
        }

        return spots.stream()
                .map(s -> new SpotFindAllResponseDto(s))
                .collect(Collectors.toList());
    }
    @ApiOperation(value = "장소 상세 정보 조회", notes = ""+
            "Long id; 장소 고유 번호\n" +
            "String name; 장소 이름\n" +
            "Address address; 주소\n" +
            "String lat; 위도\n" +
            "String lng; 경도\n" +
            "String spotAdminContact; 점주 전화번호 \n" +
            "List<SpotVisitInfoDetailsDto> spotVisitInfoDetailsDto;  방문정보 리스트 \n" +
            "Long id; 방분정보 고유번호 \n" +
            "String username; 방문한 유저 이름")
    @GetMapping("/{spotId}")
    public List<SpotFindOneResponseDto> findOneVisitInfo(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                         @RequestParam(value = "limit", defaultValue = "100") int limit,
                                                         @PathVariable("spotId") Long id){
        List<Spot> spotDetails = spotRepository.findOneDetails(id, offset, limit);

        if(spotDetails.isEmpty()) {
            throw new NullPointerException("현재 visitInfo가 없습니다. ");
        }

        return spotDetails.stream()
                .map(m -> new SpotFindOneResponseDto(m))
                .collect(Collectors.toList());
    }
}
