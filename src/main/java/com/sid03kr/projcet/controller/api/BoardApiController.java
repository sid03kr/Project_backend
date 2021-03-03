package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.Board;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.request.BoardApiRequest;
import com.sid03kr.projcet.model.network.response.BoardApiResponse;
import com.sid03kr.projcet.service.BoardApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController extends CrudController<BoardApiRequest, BoardApiResponse, Board> {

    private final BoardApiService boardApiService;

    @GetMapping("/search")
    public Header<List<BoardApiResponse>> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return boardApiService.search(pageable);
    }



}