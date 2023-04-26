package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.requestDtos.ItemRequestDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Items;
import jakarta.persistence.Index;

import java.util.List;

public class ItemTransformer {
    public static ItemResponseDto ItemtoItemResponseDto(Items items)
    {
        return ItemResponseDto.builder()
                .productName(items.getName())
                .price(items.getPrice())
                .quantity(items.getReqQuantity())
                .totalPrice(items.getPrice()* items.getReqQuantity())
                .message("Successfully Added to the Cart!")
                .build();
    }

   public static Items Check(Customer customer, ItemRequestDto itemRequestDto)
   {
       Cart cart = customer.getCart();

       if(cart.getItemsList().size()==0)
       {
           return null;
       }

       List<Items> itemsList = cart.getItemsList();

       for(Items items : itemsList)
       {
           if(items.getProduct().getId()==itemRequestDto.getProductId())
           {
               items.setReqQuantity(items.getReqQuantity()+itemRequestDto.getReqQuantity());
               cart.setCartValue(cart.getCartValue()+(items.getPrice()*itemRequestDto.getReqQuantity()));
               return items;
           }
       }

       return null;
   }
}
