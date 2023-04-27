package com.example.ECommerce.exceptions;

public class EmptyCartException extends Exception{

    public EmptyCartException(String message)
    {
        super(message);
    }
}
