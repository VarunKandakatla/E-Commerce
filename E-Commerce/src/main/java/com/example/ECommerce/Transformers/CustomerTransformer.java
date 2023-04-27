package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.requestDtos.CustomerRequestDto;
import com.example.ECommerce.dtos.responseDtos.CustomerResponseDto;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Wishlist;

import java.util.ArrayList;

public class CustomerTransformer {

    public static Customer CustomerRequesttoCustomer(CustomerRequestDto customerRequestDto)
    {

        Customer customer= Customer.builder()
                .name(customerRequestDto.getName())
                .email(customerRequestDto.getEmail())
                .mobileNo(customerRequestDto.getMobileNo())
                .address(customerRequestDto.getAddress())
                .build();

        //setting cart
        Cart cart= Cart.builder()
                .customer(customer)
                .itemsList(new ArrayList<>())
                .cartValue(0)
                .build();

        //setting wishlist
        Wishlist wishlist= Wishlist.builder()
                .customer(customer)
                .name(customer.getName())
                .build();

        customer.setCart(cart);
        customer.setWishlist(wishlist);
//        Optional Things
//        customer= Customer.builder()
//                .cart(cart)
//                .wishlist(wishlist)
//                .build();
        return customer;
    }

    public static CustomerResponseDto customertoCustomerResponseDto(Customer customer)
    {
        CustomerResponseDto customerResponseDto= CustomerResponseDto.builder()
                .name(customer.getName())
                .mobileNo(customer.getMobileNo())
                .build();

        return customerResponseDto;
    }
}
