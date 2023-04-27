package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.responseDtos.OrderResponseDto;
import com.example.ECommerce.dtos.responseDtos.OrderedItemResponseDto;
import com.example.ECommerce.entity.Cards;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.entity.Orders;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;


import java.util.Date;


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

//    public static void AssignCartToOrder(Orders orders)
//    {
//        List<Items> itemsList=orders.getItemsList();
//        for(Items items : itemsList)
//        {
//            items.setCart(orders.getCards().getCustomer().getCart());
//        }
//    }
}
