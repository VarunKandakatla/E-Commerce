package com.example.ECommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "seller")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @CreationTimestamp
    Date date=new Date();

    String enterprise;
    String ownerName;
    @Column(unique = true)
    String mobileNo;
    @Column(unique = true)
    String email;
    String address;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList=new ArrayList<>();

}
