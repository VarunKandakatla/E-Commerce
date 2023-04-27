package com.example.ECommerce.dtos.responseDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {

    String customer_Name;
    String address;
    String paymentMethod;

    String orderId;
    String date;
    int TotalItems;
    long total_OrderValue;

    List<OrderedItemResponseDto> items=new ArrayList<>();

}
