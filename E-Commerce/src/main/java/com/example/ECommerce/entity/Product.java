package com.example.ECommerce.entity;

import com.example.ECommerce.Enum.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    @Enumerated(EnumType.STRING)
    Category category;
    int quantity;
    int price;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @ManyToOne
    @JoinColumn
    Wishlist wishlist;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Items> itemsList=new ArrayList<>();
}
