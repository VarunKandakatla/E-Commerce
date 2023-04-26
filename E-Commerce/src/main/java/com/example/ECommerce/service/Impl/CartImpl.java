package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.ItemTransformer;
import com.example.ECommerce.dtos.responseDtos.CartResponseDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.exceptions.CustomerNotFound;
import com.example.ECommerce.repository.CartRepository;
import com.example.ECommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartResponseDto getCartItemsList(int cartId) throws CustomerNotFound {

        //Checking Cart is Valid or not
        Cart cart;
        try
        {
            cart=cartRepository.findById(cartId).get();
        }catch (Exception e)
        {
            throw new CustomerNotFound("Invalid Cart ID! Try Again!");
        }

        List<Items> itemsList=cart.getItemsList();

        List<ItemResponseDto> itemResponseDtoList=new ArrayList<>();

        long finalSum=0;
        for(Items items : itemsList)
        {
            finalSum+=(items.getPrice()*items.getReqQuantity());
            itemResponseDtoList.add(ItemTransformer.ItemtoItemResponseDto(items));
        }
        //re-updating cart value in delete itemcase;
        cart.setCartValue(finalSum);
        cartRepository.save(cart);
        // creating cart Response DTO
        return CartResponseDto.builder()
                .cartValue(finalSum)
                .items(itemResponseDtoList)
                .total_products(cart.getItemsList().size())
                .customerName(cart.getCustomer().getName())
                .build();
    }
}
