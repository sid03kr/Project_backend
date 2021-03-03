package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.User;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.request.UserApiRequest;
import com.sid03kr.projcet.model.network.response.UserApiResponse;
import com.sid03kr.projcet.model.network.response.UserOrderInfoApiResponse;
import com.sid03kr.projcet.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    private final UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        return userApiLogicService.orderInfo(id);
    }
    @GetMapping("/search")
    public Header<List<UserApiResponse>> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userApiLogicService.search(pageable);
    }
}

