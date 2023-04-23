package com.example.ECommerce.dtos.requestDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDto {

    String companyName;
    String ownerName;
    String mobileNo;
    String email;
    String address;

}
