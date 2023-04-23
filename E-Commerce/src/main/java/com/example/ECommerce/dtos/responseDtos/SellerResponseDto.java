package com.example.ECommerce.dtos.responseDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerResponseDto {

    String companyName;
    String ownerName;
    String mobileNo;
    String address;
}
