package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.OrderTransfomer;
import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.entity.Cards;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Orders;
import com.example.ECommerce.repository.OrderRepository;
import com.example.ECommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderResponseDto placeOrder(Cart cart, Cards cards) {


        Orders orders = OrderTransfomer.createOrder(cart,cards);

        OrderResponseDto orderResponseDto=OrderTransfomer.OrderToOrderResponseDto(orders);

        return orderResponseDto;
    }
    @Override
    public void reAssigningCart(Cards cards)
    {
//        List<Orders> ordersList=cards.getOrdersList();
//
//        if(ordersList.size()==0) return;
//
//        Orders orders=ordersList.get(ordersList.size()-1);
//
//        OrderTransfomer.AssignCartToOrder(orders);
    }
}
