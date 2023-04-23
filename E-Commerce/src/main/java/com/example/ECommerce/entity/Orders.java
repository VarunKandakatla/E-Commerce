package com.example.ECommerce.entity;

import com.example.ECommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String orderId= UUID.randomUUID().toString();
    Date date=new Date();
    String address;

    @ManyToOne
    @JoinColumn
    Cards cards;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    List<Items> itemsList=new ArrayList<>();



}
