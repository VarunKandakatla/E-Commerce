package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Emails.SendEmail;
import com.example.ECommerce.Transformers.OrderTransfomer;
import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.entity.Cards;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Orders;
import com.example.ECommerce.repository.OrderRepository;
import com.example.ECommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JavaMailSender emailSender;

    @Override
    public OrderResponseDto placeOrder(Cart cart, Cards cards) {


        Orders orders = OrderTransfomer.createOrder(cart,cards);
        //send OrderConfirmation mail
//        emailSender.send(SendEmail.sendOrderconfirmationMail(orders));

        OrderResponseDto orderResponseDto=OrderTransfomer.OrderToOrderResponseDto(orders);

        return orderResponseDto;
    }
}
