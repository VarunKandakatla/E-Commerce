package com.example.ECommerce.dtos.responseDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    String customerName;
    int total_products;
    long cartValue;
    List<ItemResponseDto> items=new ArrayList<>();
}
