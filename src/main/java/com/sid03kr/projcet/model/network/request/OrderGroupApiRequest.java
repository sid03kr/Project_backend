package com.sid03kr.projcet.model.network.request;

import com.sid03kr.projcet.model.enumclass.OrderPaymentType;
import com.sid03kr.projcet.model.enumclass.OrderType;
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
public class OrderGroupApiRequest {

    private Long id;

    private String status;

    private OrderType orderType;

    private String revAddress;

    private String revName;

    private OrderPaymentType paymentType;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Long userId;

}
