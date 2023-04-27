package com.example.ECommerce.Transformers;

import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Items;

public class CartTransformer {

    public static void ResetCart(Cart cart)
    {
        for(Items items: cart.getItemsList())
        {
            items.setCart(null);
        }

        cart.getItemsList().clear();
        cart.setCartValue(0);

    }
}
