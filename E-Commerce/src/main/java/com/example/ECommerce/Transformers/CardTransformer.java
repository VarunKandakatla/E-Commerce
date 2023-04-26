package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.requestDtos.CardRequestDto;
import com.example.ECommerce.dtos.responseDtos.CardResponseDto;
import com.example.ECommerce.entity.Cards;

public class CardTransformer {

    public static Cards cardRequestDtoToCard(CardRequestDto cardRequestDto)
    {
        return Cards.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiry(cardRequestDto.getExpiryDate())
                .build();
    }

    public static CardResponseDto cardsToCardResponseDto(Cards cards)
    {
        return CardResponseDto.builder()
                .cardNo(cards.getCardNo())
                .cardType(cards.getCardType())
                .customerName(cards.getCustomer().getName())
                .build();
    }
}
