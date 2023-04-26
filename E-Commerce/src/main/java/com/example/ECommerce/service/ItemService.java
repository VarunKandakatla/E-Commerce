package com.example.ECommerce.service;

import com.example.ECommerce.dtos.requestDtos.ItemRequestDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.exceptions.CustomerNotFound;
import com.example.ECommerce.exceptions.ProductNotFound;
import org.springframework.stereotype.Service;


public interface ItemService {
    ItemResponseDto addItem(ItemRequestDto itemRequestDto) throws CustomerNotFound, ProductNotFound;

    Object getAllItems();

    void deleteAll();

    Object getListofCartsWithProduct(int productId);


}
