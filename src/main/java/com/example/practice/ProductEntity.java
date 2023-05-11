package com.example.practice;

import org.seasar.doma.*;


@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public int price;

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
