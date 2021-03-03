package com.sid03kr.projcet.service;

import com.sid03kr.projcet.model.entity.Item;
import com.sid03kr.projcet.model.entity.Partner;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.Pagination;
import com.sid03kr.projcet.model.network.request.PartnerApiRequest;
import com.sid03kr.projcet.model.network.request.PartnerApiRequest;
import com.sid03kr.projcet.model.network.response.*;
import com.sid03kr.projcet.model.network.response.ItemApiResponse;
import com.sid03kr.projcet.model.network.response.OrderGroupApiResponse;
import com.sid03kr.projcet.model.network.response.PartnerApiResponse;
import com.sid03kr.projcet.model.network.response.PartnerInfoApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final ItemApiLogicService itemApiLogicService;

    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        Partner partner = Partner.builder()
                .type(body.getType())
                .title(body.getTitle())
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .call(body.getCall())
                .image(body.getImage())
                .build();

        Partner newPartner = baseRepository.save(partner);

        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.Error("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(partner -> {
                    partner
                            .setType(body.getType())
                            .setTitle(body.getTitle())
                            .setName(body.getName())
                            .setStatus(body.getStatus())
                            .setAddress(body.getAddress())
                            .setCall(body.getCall())
                            .setImage(body.getImage())
                            ;
                    return partner;
                })
                .map(changePartner -> baseRepository.save(changePartner))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.Error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(()->Header.Error("데이터 없음"));
    }


    public PartnerApiResponse response(Partner partner) {

        PartnerApiResponse body = PartnerApiResponse.builder()
                .type(partner.getType())
                .title(partner.getTitle())
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .call(partner.getCall())
                .image(partner.getImage())
                .build();

        return body;
    }

    public Header<PartnerInfoApiResponse> skillInfo(Long id) {

        //partner
        Partner partner = baseRepository.getOne(id);
        PartnerApiResponse partnerApiResponse = response(partner);


        // item
        List<Item> itemList = partner.getItemList();
        List<ItemApiResponse> itemApiResponseList = itemList.stream()
                .map(item -> {
                    ItemApiResponse itemApiResponse = Header.OK(itemApiLogicService.response(item)).getData();

                    // OrderGroup
                    List<OrderGroupApiResponse> orderGroupApiResponseList = item.getOrderDetailList().stream()
                            .map(orderDetail -> orderDetail.getOrderGroup())
                            .map(orderGroup -> orderGroupApiLogicService.response(orderGroup).getData())
                            .collect(Collectors.toList());
                    itemApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

                    return itemApiResponse;
                })
                .collect(Collectors.toList());

        partnerApiResponse.setItemApiResponseList(itemApiResponseList);
        PartnerInfoApiResponse partnerInfoApiResponse = PartnerInfoApiResponse.builder()
                .partnerApiResponse(partnerApiResponse)
                .build();

        return Header.OK(partnerInfoApiResponse);
    }

    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();
        return Header.OK(partnerApiResponseList, pagination);
    }
}
