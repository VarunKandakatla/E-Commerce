package com.example.ECommerce.service.Impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.ECommerce.Transformers.*;
import com.example.ECommerce.dtos.requestDtos.CartCheckOutRequestDto;
import com.example.ECommerce.dtos.responseDtos.CartResponseDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.dtos.responseDtos.OrderedItemResponseDto;
import com.example.ECommerce.entity.*;
import com.example.ECommerce.exceptions.CustomerNotFound;
import com.example.ECommerce.exceptions.EmptyCartException;
import com.example.ECommerce.repository.CartRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.repository.ItemRepository;
import com.example.ECommerce.service.CartService;
import com.example.ECommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public CartResponseDto getCartItemsList(int cartId) throws CustomerNotFound, EmptyCartException {

        //Checking Cart is Valid or not

        Cart cart;
        try{
            cart=cartRepository.findById(cartId).get();
        }catch (Exception e)
        {
            throw new CustomerNotFound("Invalid Cart Id");
        }

        if(cart.getItemsList().size()==0)
        {
            throw new RuntimeException("Empty Cart");
        }

//        System.out.println("InsideCart"+ cart.getItemsList().size());

        long cartSum=0;
        //Have to use itemResponseDto
        List<OrderedItemResponseDto> list=new ArrayList<>();
        List<Items> itemsList=cart.getItemsList();

        for(Items items : itemsList)
        {
            //count the cart value
            cartSum=cartSum+(items.getReqQuantity()*items.getPrice());
            list.add(ItemTransformer.itemToOrderedItemResponseDto(items));
        }

        //updating the cart value, helps incase the items are deleted
        cart.setCartValue(cartSum);

        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartValue(cart.getCartValue())
                .total_products(cart.getItemsList().size())
                .items(list)
                .build();
    }

    @Override
    public OrderResponseDto cartCheckOut(CartCheckOutRequestDto cartCheckOutRequestDto) throws CustomerNotFound, EmptyCartException {

        //Check Cart
        Cart cart;
        try
        {
            cart=cartRepository.findById(cartCheckOutRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerNotFound("Invalid Customer!!!");
        }

        //Check if cart is Empty
        if(cart.getItemsList().size()==0)
        {
            throw new EmptyCartException("Empty Cart! Pls add some items!!!");
        }

        //CheckCard details

        List<Cards> cardsList= cart.getCustomer().getCardsList();

        Cards card = CardTransformer.ValidateCardDetails(cartCheckOutRequestDto,cardsList);

        if(card==null)
        {
            throw new RuntimeException("Card not found!!!");
        }

        if(card.getCvv()!=cartCheckOutRequestDto.getCvv())
        {
            throw new RuntimeException("Incorrect CVV Details!!!");
        }

        //All Success need to place Order;
        //reducing Quantity
        try
        {
            ProductTransfomer.ReduceQuantity(cart.getItemsList());
        }
        catch (Exception e)
        {
            throw new RuntimeException("Insuffient Quantity! Product out of Stock!");
        }

        //Placing Order
        OrderResponseDto orderResponseDto = orderService.placeOrder(cart,card);

        //resetting cart
        CartTransformer.ResetCart(cart);

        //orderResponse
        return orderResponseDto;

    }

    @Override
    public Object getItemsinCart(int cartID) {
        List<Items> itemsList=cartRepository.findById(cartID).get().getItemsList();

        List<ItemResponseDto> list=new ArrayList<>();

        for(Items items : itemsList)
        {
            list.add(ItemTransformer.ItemtoItemResponseDto(items));
        }

        return list;
    }

    @Override
    public String cleanCart(int cartId) throws CustomerNotFound {

        Cart cart;
        try
        {
            cart=cartRepository.findById(cartId).get();
        }
        catch (Exception e)
        {
            throw new CustomerNotFound("Invalid Cart ID!!!");
        }

        cart.setCartValue(0);
        for(Items items: cart.getItemsList())
        {
           items.setCart(null);
           itemRepository.delete(items);
        }
        cart.getItemsList().clear();
        cartRepository.save(cart);
        return "Resetted and Size is: "+cart.getItemsList().size();
    }

}
