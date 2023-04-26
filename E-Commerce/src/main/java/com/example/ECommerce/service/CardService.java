package com.example.ECommerce.service;

import com.example.ECommerce.dtos.requestDtos.CardRequestDto;
import com.example.ECommerce.exceptions.CardAlreadyExists;

public interface CardService {
    Object addCard(CardRequestDto cardRequestDto) throws CardAlreadyExists;
}
