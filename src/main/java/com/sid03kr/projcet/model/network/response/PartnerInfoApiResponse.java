package com.sid03kr.projcet.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerInfoApiResponse {

    private PartnerApiResponse partnerApiResponse;
}
