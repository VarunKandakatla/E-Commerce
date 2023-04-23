package com.example.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Data contains all getters & setters & No args & all args constructors
@Data
//@FieldDefaults  sets all attributes private
@Builder
//@Builder is another way of creating objects
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @CreationTimestamp
    Date date;

    String name;
    @Column(unique = true)
    String mobileNo;
    @Column(unique = true)
    String email;
    String address;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Wishlist wishlist;
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Cards> cardsList=new ArrayList<>();


}
