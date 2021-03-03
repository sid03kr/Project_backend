package com.sid03kr.projcet.service;

import com.sid03kr.projcet.model.entity.OrderDetail;
import com.sid03kr.projcet.model.entity.OrderGroup;
import com.sid03kr.projcet.model.entity.User;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.Pagination;
import com.sid03kr.projcet.model.network.request.UserApiRequest;
import com.sid03kr.projcet.model.network.response.ItemApiResponse;
import com.sid03kr.projcet.model.network.response.OrderGroupApiResponse;
import com.sid03kr.projcet.model.network.response.UserApiResponse;
import com.sid03kr.projcet.model.network.response.UserOrderInfoApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {


    private final OrderGroupApiLogicService orderGroupApiLogicService;

    private final ItemApiLogicService itemApiLogicService;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(userApiRequest.getStatus())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(
                        ()->Header.Error("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터 를 찾고
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3. data -> update
            // id
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
            ;
            return user;

        })
                .map(user -> baseRepository.save(user))             // update -> newUser
                .map(this::response)                        // userApiResponse
                .map(Header::OK)
                .orElseGet(()->Header.Error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        // 2. repository -> delete
        return optional.map(user ->{
            baseRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(()->Header.Error("데이터 없음"));
    }

    private UserApiResponse response(User user){
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return userApiResponse;
    }

    public Header<UserOrderInfoApiResponse> orderInfo (Long id) {

        // User

        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        // orderGroup
        List<OrderGroup> orderGroupsList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupsList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();

                    // Item
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(OrderDetail::getItem)

                            .map(item -> Header.OK(itemApiLogicService.response(item)).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;

                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();
        return Header.OK(userApiResponseList, pagination);
    }
}