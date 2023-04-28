package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.dtos.responseDtos.OrderedItemResponseDto;
import com.example.ECommerce.entity.Cards;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.entity.Orders;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;


public class OrderTransfomer {

    public static Orders createOrder(Cart cart, Cards cards)
    {
        Orders orders=new Orders();
        orders.setAddress(cart.getCustomer().getAddress());
        orders.setCards(cards);
        orders.getItemsList().addAll(cart.getItemsList());

        cards.getOrdersList().add(orders);

        return orders;
    }

    public static OrderResponseDto OrderToOrderResponseDto(Orders orders)
    {
        OrderResponseDto orderResponseDto= new OrderResponseDto();

        orderResponseDto.setOrderId(orders.getOrderId());
        orderResponseDto.setCustomer_Name(orders.getCards().getCustomer().getName());
        orderResponseDto.setAddress(orders.getAddress());
        orderResponseDto.setDate(new Date().toString());
        orderResponseDto.setPaymentMethod(orders.getCards().getCardType().name());
//        orderResponseDto.setTotal_OrderValue(orders.getCards().getCustomer().getCart().getCartValue());
        long totalValue=0;
        for(Items items: orders.getItemsList())
        {
            totalValue=totalValue+(items.getReqQuantity()*items.getPrice());
            items.setOrders(orders);
            orderResponseDto.getItems().add(ItemTransformer.itemToOrderedItemResponseDto(items));
        }
        orderResponseDto.setTotal_OrderValue(totalValue);
        orderResponseDto.setTotalItems(orderResponseDto.getItems().size());

        return orderResponseDto;
    }

    public static String SetMessage(Orders orders)
    {
        //Preparing list of items
        String itemslist="";

        List<Items> items= orders.getItemsList();

        for(int i=1;i<=items.size();i++)
        {
            Items items1=items.get(i-1);
            itemslist+=i+". "+items1.getName()+",  "+items1.getPrice()+"₹, Quantity: "+items1.getReqQuantity()
                    +"  ItemTotalCost = "+items1.getPrice()*items1.getReqQuantity()+"₹\n";
        }


        String text=
                "Hi "+orders.getCards().getCustomer().getName()+
                "!\nCongratulations! Your Order is Successfull!\n" +
                        "Thank you for Shopping!\n" +
                        "Have a Great Day😊😊!\n\n"+

                        "Here is your Order Details:-\n\n"+
                        "Order Id : "+orders.getOrderId().toString()+"\n" +
                        "Order Status : SUCCESSFULL\n" +
                        "Order date : "+new Date().toString()+"\n" +
                        "Payment Method : "+orders.getCards().getCardType().toString()+"\n"+
                        "Order Value : "+orders.getCards().getCustomer().getCart().getCartValue()+"₹\n" +
                        "Total Items : "+orders.getItemsList().size()+"\n\n" +
                        "Items List :-\n"+itemslist;

        //return textl
        return text;

    }
}
