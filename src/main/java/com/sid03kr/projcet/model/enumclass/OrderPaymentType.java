package com.sid03kr.projcet.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderPaymentType {
    CARD(0,"카드","카드로 결제"),
    CASH(1,"현금","현금으로 결제"),
    CHECK_CARD(2,"",""),
    BANK_TRANSFER(3,"","")
    ;

    private Integer id;
    private String title;
    private String description;
}
