package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.StudyApplicationTests;
import com.sid03kr.projcet.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // DI
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test0";
        String password ="Test0";
        String status = " registered";
        String email = "Test@naver.com";
        String phoneNumber = "010-1110-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy ="AdminServer";



        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
//        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
//                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("");

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("--------------주문묶음---------------");
            System.out.println("수령인 : "+orderGroup.getRevName());
            System.out.println("수령지 : "+orderGroup.getRevAddress());

            System.out.println("총금액 : "+orderGroup.getTotalPrice());
            System.out.println("총수량 : "+orderGroup.getTotalQuantity());

            System.out.println("--------------주문상세---------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
//                System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
//                System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCall());
                System.out.println("주문의 상태 : "+orderDetail.getStatus());
                System.out.println("주문 도착예정일자 : "+orderDetail.getArrivalDate());

            });
        });

        Assert.assertNotNull(user);



    }
    @Test
    public void update() {

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("update");
            selectUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(selectUser);
        });

    }
    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());   // 존재해야한다

        user.ifPresent(selectUser-> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent()); // 없어야한다
    }
}
