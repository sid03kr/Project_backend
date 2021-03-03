package com.sid03kr.projcet.service;

import com.sid03kr.projcet.model.entity.Item;
import com.sid03kr.projcet.model.network.Header;
import com.sid03kr.projcet.model.network.Pagination;
import com.sid03kr.projcet.model.network.request.ItemApiRequest;
import com.sid03kr.projcet.model.network.response.ItemApiResponse;
import com.sid03kr.projcet.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    private final PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        return Optional.ofNullable(request.getData())
                .map(body-> {
                    Item item = Item.builder()
                            .title(body.getTitle())
                            .content(body.getContent())
                            .image(body.getImage())
                            .price(body.getPrice())
                            .partner(partnerRepository.getOne(body.getPartnerId()))
                            .build();
                    return item;
                })
                .map(newItem -> baseRepository.save(newItem))
                .map(newItem -> response(newItem))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(item -> response(item))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {


        return Optional.ofNullable(request.getData())
                .map(body -> {
                    return baseRepository.findById(body.getId());
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(item -> {
                    ItemApiRequest body = request.getData();
                    item
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setImage(body.getImage())
                            .setPrice(body.getPrice())
                            .setPartner(partnerRepository.getOne(body.getPartnerId()))
                            ;
                    return item;
                })
                .map(changeItem -> baseRepository.save(changeItem))
                .map(newItem -> response(newItem))
                .map(Header::OK)
                .orElseGet(()->Header.Error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()->Header.Error("데이터 없음"));
    }

    public ItemApiResponse response(Item item){

        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .content(item.getContent())
                .image(item.getImage())
                .price(item.getPrice())
                .partnerId(item.getPartner().getId())
                .build();

        return body;
    }


    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);
        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();
        return Header.OK(itemApiResponseList, pagination);

    }
}