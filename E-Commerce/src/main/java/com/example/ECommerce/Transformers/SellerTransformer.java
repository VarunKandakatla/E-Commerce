package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.requestDtos.SellerRequestDto;
import com.example.ECommerce.dtos.responseDtos.SellerResponseDto;
import com.example.ECommerce.entity.Seller;

public class SellerTransformer {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto)
    {
        Seller seller= Seller.builder()
                .ownerName(sellerRequestDto.getOwnerName())
                .enterprise(sellerRequestDto.getCompanyName())
                .mobileNo(sellerRequestDto.getMobileNo())
                .email(sellerRequestDto.getEmail())
                .address(sellerRequestDto.getAddress())
                .build();

        return  seller;
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller)
    {
        SellerResponseDto sellerResponseDto= SellerResponseDto.builder()
                .companyName(seller.getEnterprise())
                .ownerName(seller.getOwnerName())
                .mobileNo(seller.getMobileNo())
                .address(seller.getAddress())
                .build();

        return sellerResponseDto;
    }

    public static Seller updateSeller(Seller seller, SellerRequestDto sellerRequestDto)
    {
        seller.setEnterprise(sellerRequestDto.getCompanyName());
        seller.setAddress(sellerRequestDto.getAddress());
        seller.setEmail(sellerRequestDto.getEmail());
        seller.setMobileNo(sellerRequestDto.getMobileNo());
        seller.setOwnerName(sellerRequestDto.getOwnerName());
        return  seller;
    }
}
