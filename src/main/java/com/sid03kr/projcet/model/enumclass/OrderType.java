package com.sid03kr.projcet.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {

    ALL(0,"묶음발송","모든상품 묶음 형태 발송"),
    EACH(1,"개별발송","모든 상품 준비되는대로 발송")
    ;

    private Integer id;

    private String title;

    private String description;
}
