package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository <AdminUser, Long> {
}
