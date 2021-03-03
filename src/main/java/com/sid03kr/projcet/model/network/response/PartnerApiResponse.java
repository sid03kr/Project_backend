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
public class PartnerApiResponse {

    private Long id;

    private String name;

    private String status;

    private String address;

    private String call;

    private String type;

    private String title;

    private String image;

    private List<ItemApiResponse> itemApiResponseList;
    
}
