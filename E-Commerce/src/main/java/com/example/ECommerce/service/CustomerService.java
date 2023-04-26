package com.example.ECommerce.service;

import com.example.ECommerce.dtos.requestDtos.CustomerRequestDto;
import com.example.ECommerce.dtos.responseDtos.CustomerResponseDto;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.exceptions.MobileNotFound;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

   List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateNameOfCustomer(String name, String mobile) throws MobileNotFound;

    String deleteCustomer(String mobile) throws MobileNotFound;


}
