package com.sid03kr.projcet.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiRequest {

    private Long id;

    private String name;

    private String status;

    private String address;

    private String call;

    private String type;

    private String title;

    private String image;

}
