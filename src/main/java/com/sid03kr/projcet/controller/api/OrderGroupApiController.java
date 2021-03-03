package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.OrderGroup;
import com.sid03kr.projcet.model.network.request.OrderGroupApiRequest;
import com.sid03kr.projcet.model.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {


}
