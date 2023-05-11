package com.example.practice;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class ProductEntity {
    @Id
    public int id;

    public String name;

    public int price;


    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

}
