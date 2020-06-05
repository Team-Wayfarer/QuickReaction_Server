package org.quickresponse.qr.service.code;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.code.Code;
import org.quickresponse.qr.domain.code.CodeRepository;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.spot.SpotRepository;
import org.quickresponse.qr.service.code.dto.SpotResponseDtoByCode;
import org.quickresponse.qr.util.CodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    private final SpotRepository spotRepository;

    public SpotResponseDtoByCode findById(Long id) {
        Code code = codeRepository.findById(id);
        return new SpotResponseDtoByCode(code.getSpot().getId());
    }

    @Transactional
    public Code save(Long spot_id) throws Exception {
        Spot spot = spotRepository.findOne(spot_id);
        String url = CodeGenerator.create(spot_id);
        return codeRepository.save(spot, url);
    }

    public String getUrl(Long id) {
        return codeRepository.findById(id).getUrl();
    }
}
