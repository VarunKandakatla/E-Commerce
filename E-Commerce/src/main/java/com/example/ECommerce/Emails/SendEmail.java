package com.example.ECommerce.Emails;

import com.example.ECommerce.Transformers.OrderTransfomer;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class SendEmail {
    public static SimpleMailMessage sendWelcomeEmail(Customer customer)
    {
        String text= welcomeText(customer);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("buybharath@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Welcome to BuyBharathStore😊!");
        message.setText(text);
       return message;
    }

    public static String welcomeText(Customer customer)
    {
        String text="Hi "+customer.getName()+"!\n" +
                "Welcome to the BuyBharathStore, an Indian Ecommerce Website.\n" +
                "What are you waiting for? A lot of products are waiting for you! " +
                "Have a look and make your cart overloaded!\n\n" +
                "Hope You Enjoys a lot and Have a Great Experience\n" +
                "Thank you "+customer.getName()+"😊\n\n" +
                "Be Indian, Buy Indian!\n" +
                "BuyBharathProducts - Indian Made";
        return text;
    }

    public static SimpleMailMessage sendOrderconfirmationMail(Orders orders)
    {
        //customer MailId
        Customer customer=orders.getCards().getCustomer();
        String customerMailId=customer.getEmail();

        //Setting Text
        String text= OrderTransfomer.SetMessage(orders);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("buybharath@gmail.com");
        message.setTo(customerMailId);
        message.setSubject("Order Confirmation! Keep Shopping "+customer.getName()+" :-)");
        message.setText(text);
        return message;
    }
}
