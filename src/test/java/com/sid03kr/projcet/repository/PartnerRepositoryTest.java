package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.StudyApplicationTests;
import com.sid03kr.projcet.model.entity.Partner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends StudyApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {

        String name = "성일도";
        String status = "무직";
        String address = "도봉구 해등로 113";
        String call = "010-3032-4520";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCall(call);
//        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
//        partner.setCategoryId(categoryId);


        Partner newPartner = partnerRepository.save(partner);
        Assert.assertNotNull(newPartner);
        Assert.assertEquals(newPartner.getName(), name);

    }

}
