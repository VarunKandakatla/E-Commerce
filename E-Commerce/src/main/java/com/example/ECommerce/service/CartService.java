package com.example.ECommerce.service;

import com.example.ECommerce.dtos.responseDtos.CartResponseDto;
import com.example.ECommerce.exceptions.CustomerNotFound;

import java.util.List;

public interface CartService {
    CartResponseDto getCartItemsList(int cartId) throws CustomerNotFound;
}
