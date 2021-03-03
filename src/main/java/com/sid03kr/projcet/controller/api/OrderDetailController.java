package com.sid03kr.projcet.controller.api;

import com.sid03kr.projcet.controller.CrudController;
import com.sid03kr.projcet.model.entity.OrderDetail;
import com.sid03kr.projcet.model.network.request.OrderDetailApiRequest;
import com.sid03kr.projcet.model.network.response.OrderDetailApiResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orderDetail")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {


}
