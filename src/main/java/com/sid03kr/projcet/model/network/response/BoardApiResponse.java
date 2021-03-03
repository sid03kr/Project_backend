package com.sid03kr.projcet.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardApiResponse {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createdAt;

    private String createdBy;

}
