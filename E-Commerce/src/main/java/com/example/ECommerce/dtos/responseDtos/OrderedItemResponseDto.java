package com.example.ECommerce.dtos.responseDtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderedItemResponseDto {
    String product_Name;
    String manufacturer;
    int price;
    int quantity;
    int totalPrice;
}
