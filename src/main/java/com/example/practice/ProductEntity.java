package com.example.practice;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Column;

/**
 * ProductEntityクラス
 * レコードに対応するオブジェクトクラス
 *
 * @author kohama
 */
@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    public ProductEntity(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductEntity() {
    }
}