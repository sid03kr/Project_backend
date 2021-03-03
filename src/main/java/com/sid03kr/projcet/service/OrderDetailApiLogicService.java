package com.sid03kr.projcet.service;

import com.sid03kr.projcet.model.entity.OrderDetail;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.request.OrderDetailApiRequest;
import com.sid03kr.projcet.model.network.response.OrderDetailApiResponse;
import com.sid03kr.projcet.repository.ItemRepository;
import com.sid03kr.projcet.repository.OrderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    private final OrderGroupRepository orderGroupRepository;

    private final ItemRepository itemRepository;


    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .item(itemRepository.getOne(body.getItemId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.Error("데이터 없음"));


    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(orderDetail -> {
                    orderDetail
                            .setStatus(body.getStatus())
                            .setArrivalDate(body.getArrivalDate())
                            .setQuantity(body.getQuantity())
                            .setTotalPrice(body.getTotalPrice())
                            .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                            .setItem(itemRepository.getOne(body.getItemId()))
                            ;
                    return orderDetail;
                })
                .map(changeOrderDetail -> baseRepository.save(changeOrderDetail))
                .map(this::response)
                .orElseGet(()-> Header.Error("데이터 없음"));

    }


    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.Error("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {

        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();

        return Header.OK(body);
    }
}
