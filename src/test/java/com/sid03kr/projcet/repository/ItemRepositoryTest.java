package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.StudyApplicationTests;
import com.sid03kr.projcet.model.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {

        Item item = new Item();
//        item.setStatus("Test");
//        item.setName("컴퓨터");
        item.setTitle("Computer");
        item.setContent("좋은거");
//        item.setPrice(900000);
//        item.setBrandName("samsung");
//        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("partner");
//        item.setPartnerId(1L);


        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read() {

        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());

    }
}
