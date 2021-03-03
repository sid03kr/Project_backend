package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.StudyApplicationTests;
import com.sid03kr.projcet.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("admin user");
        adminUser.setPassword("123");
        adminUser.setStatus("registered");
        adminUser.setRole("super");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);

    }


}
