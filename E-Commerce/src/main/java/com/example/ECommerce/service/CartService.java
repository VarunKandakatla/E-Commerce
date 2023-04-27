package com.example.ECommerce.service;

import com.example.ECommerce.dtos.requestDtos.CartCheckOutRequestDto;
import com.example.ECommerce.dtos.responseDtos.CartResponseDto;
import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.exceptions.CustomerNotFound;
import com.example.ECommerce.exceptions.EmptyCartException;

import java.util.List;

public interface CartService {
    CartResponseDto getCartItemsList(int cartId) throws CustomerNotFound, EmptyCartException;

    OrderResponseDto cartCheckOut(CartCheckOutRequestDto cartCheckOutRequestDto) throws CustomerNotFound, EmptyCartException;

    Object getItemsinCart(int cartID);

    Object cleanCart(int cartId) throws CustomerNotFound;
}
