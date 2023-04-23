package com.example.ECommerce.service;

import com.example.ECommerce.dtos.requestDtos.SellerRequestDto;
import com.example.ECommerce.dtos.responseDtos.SellerResponseDto;
import com.example.ECommerce.exceptions.MobileNotFound;

import java.util.List;

public interface SellerService {
    SellerResponseDto updateCompanyName(int id, String name);

    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    List<SellerResponseDto> getAllSellers();

    String deleteSeller(int id, String mobNo) throws MobileNotFound;
}
