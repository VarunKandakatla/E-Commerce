package com.example.ECommerce.dtos.requestDtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCheckOutRequestDto {
    int customerId;
    String cardNo;
    int cvv;
}
