package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    //
    // データベースから商品一覧を取得するメソッド
    //
    public List<ProductEntity> getProductList() {
        List<ProductEntity> productList = productDao.selectAll();
        return productList;
    }

    //
    // データベースにデータを追加するメソッド
    //
    public void insertProduct(String name, int price){
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        productDao.insert(product);
    }
}
