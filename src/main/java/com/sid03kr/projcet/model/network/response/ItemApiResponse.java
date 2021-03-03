package com.sid03kr.projcet.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemApiResponse {

    private Long id;

    private String title;

    private String content;

    private Long partnerId;

    private String image;

    private Integer price;

    private List<OrderGroupApiResponse> orderGroupApiResponseList;
}
