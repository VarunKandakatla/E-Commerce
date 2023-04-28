package com.example.ECommerce.dtos.requestDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDtoByName {

    String ProductName;
    Integer reqQuantity;
    Integer customerId;
}
