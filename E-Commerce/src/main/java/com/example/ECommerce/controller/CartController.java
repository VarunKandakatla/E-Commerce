package com.example.ECommerce.controller;

import com.example.ECommerce.dtos.requestDtos.CartCheckOutRequestDto;
import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.exceptions.CustomerNotFound;
import com.example.ECommerce.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/getCartItemsList")
    public ResponseEntity getCartItemList(@RequestParam int cartId)
    {
        try {
            return new ResponseEntity<>(cartService.getCartItemsList(cartId), HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/CartCheckout")
    public ResponseEntity CartCheckOut(@RequestBody CartCheckOutRequestDto cartCheckOutRequestDto)
    {
        try
        {
            OrderResponseDto orderResponseDto=cartService.cartCheckOut(cartCheckOutRequestDto);
//            String str= String.valueOf(CleanCart(cartCheckOutRequestDto.getCustomerId()));
            return new ResponseEntity(orderResponseDto,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getItemsInCart")
    public ResponseEntity getItemsInCart(@RequestParam int cartID){
        return new ResponseEntity<>(cartService.getItemsinCart(cartID),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/ResetCart")
    public ResponseEntity CleanCart(@RequestParam int cartId) throws CustomerNotFound {
        return new ResponseEntity<>(cartService.cleanCart(cartId),HttpStatus.ACCEPTED);
    }
}
