package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.dto.SpotFindAllResponseDto;
import org.quickresponse.qr.dto.SpotFindOneResponseDto;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.dto.SpotSaveResponseDto;
import org.quickresponse.qr.repository.SpotRepository;
import org.quickresponse.qr.service.SpotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/spots")
public class SpotController {

    private final SpotService spotService;
    private final SpotRepository spotRepository;

    @PostMapping("/{spotAdminId}")                                       //새로운 spot 생성
    public SpotSaveResponseDto save(@PathVariable("spotAdminId") Long spotAdminId,
                                    @RequestBody SpotSaveRequestDto spotSaveRequestDto){
        Long joinId = spotService.join(spotSaveRequestDto, spotAdminId);
        return new SpotSaveResponseDto(joinId);
    }

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

    @GetMapping("/{spotId}")                                              //id로 spot의 vistinfo 조회
    public List<SpotFindOneResponseDto> findOneVisitInfo(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                         @RequestParam(value = "limit", defaultValue = "100") int limit,
                                                         @PathVariable("spotId") Long id){
        List<Spot> spotDetails = spotRepository.findOneDetails(id, offset, limit);

        if(spotDetails.isEmpty())
            throw new NullPointerException("현재 visitInfo가 없습니다. ");

        return spotDetails.stream()
                .map(m -> new SpotFindOneResponseDto(m))
                .collect(Collectors.toList());
    }
}
