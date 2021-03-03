package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.Partner;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.request.PartnerApiRequest;
import com.sid03kr.projcet.model.network.response.PartnerApiResponse;
import com.sid03kr.projcet.model.network.response.PartnerInfoApiResponse;
import com.sid03kr.projcet.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final PartnerApiLogicService partnerApiLogicService;

    @GetMapping("/{id}/skillInfo")
    public Header<PartnerInfoApiResponse> skillInfo(@PathVariable Long id) {
        return partnerApiLogicService.skillInfo(id);
    }

    @GetMapping("/search")
    public Header<List<PartnerApiResponse>> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return partnerApiLogicService.search(pageable);
    }


}
