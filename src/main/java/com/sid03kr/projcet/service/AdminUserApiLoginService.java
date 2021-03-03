package com.sid03kr.projcet.service;

import com.sid03kr.projcet.model.entity.AdminUser;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.request.AdminUserRequest;
import com.sid03kr.projcet.model.network.response.AdminUserResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLoginService extends BaseService<AdminUserRequest, AdminUserResponse, AdminUser> {


    @Override
    public Header<AdminUserResponse> create(Header<AdminUserRequest> request) {

        AdminUserRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .lastLoginAt(body.getLastLoginAt())
                .passwordUpdatedAt(body.getPasswordUpdatedAt())
                .loginFailCount(body.getLoginFailCount())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                .build();

        AdminUser newAdminUser = baseRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.Error("데이터 없음"));
    }

    @Override
    public Header<AdminUserResponse> update(Header<AdminUserRequest> request) {

        AdminUserRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(adminUser -> {
                    adminUser
                            .setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLoginFailCount(body.getLoginFailCount())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                    ;
                    return adminUser;
                })
                .map(newEntityAdminUser -> baseRepository.save(newEntityAdminUser))
                .map(this::response)
                .orElseGet(() -> Header.Error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(() -> Header.Error("데이터 없음"));
    }

    public Header<AdminUserResponse> response(AdminUser adminUser) {

        AdminUserResponse body = AdminUserResponse.builder()
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return Header.OK(body);
    }
}
