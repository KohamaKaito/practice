package com.example.practice;

import com.example.practice.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductEntityTest {

    @Test
    public void testProductEntity() {
        // テストデータの作成
        int id = 1;
        String name = "Sample Product";
        int price = 1000;

        // ProductEntityオブジェクトの生成
        ProductEntity entity = new ProductEntity(id, name, price);

        // フィールドの値の取得と検証
        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(price, entity.getPrice());
    }
}