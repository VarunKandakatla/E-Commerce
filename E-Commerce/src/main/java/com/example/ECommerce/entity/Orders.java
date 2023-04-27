package com.example.ECommerce.entity;

import com.example.ECommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

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
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String orderId= UUID.randomUUID().toString();
    @CreationTimestamp
    Date date;

    String address;

    @ManyToOne
    @JoinColumn
    Cards cards;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    List<Items> itemsList=new ArrayList<>();



}
