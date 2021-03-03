package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.StudyApplicationTests;
import com.sid03kr.projcet.model.entity.OrderGroup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {

        OrderGroup orderGroup = new OrderGroup();

        orderGroup.setStatus("complete");
//        orderGroup.setOrderType("CARD");
        orderGroup.setRevAddress("제주도");
        orderGroup.setRevName("성일도");
//        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("partner");
//        orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOrderGroup);
    }
}
