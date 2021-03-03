package com.sid03kr.projcet.service;

import com.sid03kr.projcet.model.entity.Board;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.Pagination;
import com.sid03kr.projcet.model.network.request.BoardApiRequest;
import com.sid03kr.projcet.model.network.response.BoardApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardApiService extends BaseService<BoardApiRequest, BoardApiResponse, Board> {


    @Override
    public Header<BoardApiResponse> create(Header<BoardApiRequest> request) {

        BoardApiRequest boardApiRequest = request.getData();

        Board board = Board.builder()
                .id(boardApiRequest.getId())
                .title(boardApiRequest.getTitle())
                .contents(boardApiRequest.getContents())
                .createdAt(LocalDateTime.now())
                .createdBy(boardApiRequest.getCreatedBy())
                .build();
        Board newBoard = baseRepository.save(board);

        return Header.OK(response(newBoard));
    }

    @Override
    public Header<BoardApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.Error("데이터 없음"));
    }

    @Override
    public Header<BoardApiResponse> update(Header<BoardApiRequest> request) {

        BoardApiRequest boardApiRequest = request.getData();

        Optional<Board> optional = baseRepository.findById(boardApiRequest.getId());

        return optional.map(board -> {
            board.setId(boardApiRequest.getId())
                    .setTitle(boardApiRequest.getTitle())
                    .setContents(boardApiRequest.getContents())
                    .setCreatedAt(LocalDateTime.now())
                    .setCreatedBy(boardApiRequest.getCreatedBy());


            return board;
        })
                .map(board -> baseRepository.save(board))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()-> Header.Error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<Board> optional = baseRepository.findById(id);

        return optional.map(board -> {baseRepository.delete(board);
        return Header.OK();}
        ).orElseGet(() -> Header.Error(" 데이터 없음"));
    }

    public Header<List<BoardApiResponse>> search(Pageable pageable) {
        Page<Board> boards = baseRepository.findAll(pageable);
        List<BoardApiResponse> boardApiResponseList = boards.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(boards.getTotalPages())
                .totalElements(boards.getTotalElements())
                .currentPage(boards.getNumber())
                .currentElements(boards.getNumberOfElements())
                .build();
        return Header.OK(boardApiResponseList, pagination);
    }

    public BoardApiResponse response(Board board) {

        BoardApiResponse body = BoardApiResponse.builder()
                .title(board.getTitle())
                .id(board.getId())
                .contents(board.getContents())
                .createdAt(board.getCreatedAt())
                .createdBy(board.getCreatedBy())
                .build();

        return body;
    }

}