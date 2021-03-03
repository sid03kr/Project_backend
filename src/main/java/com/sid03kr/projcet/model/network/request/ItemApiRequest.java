package com.sid03kr.projcet.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemApiRequest {

    private Long id;

    private String title;

    private String content;

    private String image;

    private Integer price;

    private Long partnerId;

}
