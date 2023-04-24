package com.example.ECommerce.service;

import com.example.ECommerce.dtos.requestDtos.SellerRequestDto;
import com.example.ECommerce.dtos.responseDtos.SellerResponseDto;
import com.example.ECommerce.exceptions.MobileNotFound;
import com.example.ECommerce.exceptions.SellerNotFound;

import java.util.List;

public interface SellerService {
    SellerResponseDto updateCompanyName(int id, String name);

    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    List<SellerResponseDto> getAllSellers();

    String deleteSeller(int id, String mobNo) throws MobileNotFound;

    SellerResponseDto getSellerbyEmail(String mailId) throws SellerNotFound;

    SellerResponseDto getSellerbyId(int id) throws SellerNotFound;

    SellerResponseDto updateSellerDetailsByEmail(String mailId,SellerRequestDto sellerRequestDto) throws SellerNotFound;

    Object deleteSellerbyEmail(String emailID) throws SellerNotFound;
}
