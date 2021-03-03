package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.Item;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.request.ItemApiRequest;
import com.sid03kr.projcet.model.network.response.ItemApiResponse;
import com.sid03kr.projcet.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

    private final ItemApiLogicService itemApiLogicService;

    @GetMapping("/search")
    public Header<List<ItemApiResponse>> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return itemApiLogicService.search(pageable);
    }
}
