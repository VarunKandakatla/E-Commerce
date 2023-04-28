package com.example.ECommerce.service;

import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.dtos.responseDtos.OrderedItemResponseDto;
import com.example.ECommerce.entity.Cards;
import com.example.ECommerce.entity.Cart;

public interface OrderService {

    OrderResponseDto placeOrder(Cart cart, Cards cards);

}
