package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.StudyApplicationTests;
import com.sid03kr.projcet.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("status");
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("adminServer");
//        orderDetail.setOrderGroupId(1L);
//        orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(newOrderDetail);


    }

}
