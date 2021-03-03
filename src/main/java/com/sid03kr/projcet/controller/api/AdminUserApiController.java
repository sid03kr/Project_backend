package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.AdminUser;
import com.sid03kr.projcet.model.network.request.AdminUserRequest;
import com.sid03kr.projcet.model.network.response.AdminUserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminUser")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserApiController extends CrudController<AdminUserRequest, AdminUserResponse, AdminUser> {


}
