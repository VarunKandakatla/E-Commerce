package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.requestDtos.CardRequestDto;
import com.example.ECommerce.dtos.requestDtos.CartCheckOutRequestDto;
import com.example.ECommerce.dtos.responseDtos.CardResponseDto;
import com.example.ECommerce.entity.Cards;

import java.util.List;

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

    public static Cards ValidateCardDetails(CartCheckOutRequestDto cartCheckOutRequestDto, List<Cards> cardsList)
    {
        for(Cards cards : cardsList)
        {
            if(cards.getCardNo().equals(cartCheckOutRequestDto.getCardNo()))
            {
                return cards;
            }
        }

        return null;
    }
}
