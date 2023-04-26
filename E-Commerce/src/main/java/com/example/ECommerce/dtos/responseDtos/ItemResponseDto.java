package com.example.ECommerce.dtos.responseDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {

    String productName;
    int price;
    int quantity;
    int totalPrice=price*quantity;

    String message="Item added to the cart";
}
