package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    //
    // データベースから商品一覧を取得するメソッド
    //
    public List<ProductEntity> selectProduct() {
        List<ProductEntity> productList = productDao.selectAll();
        return productList;
    }

    //
    // データベースにデータを追加するメソッド
    //
    public void insertProduct(String name, int price) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        productDao.insertProduct(product);
    }

    //
    // データベースのデータを更新するメソッド
    //
    @Transactional
    public void updateProduct(int id, String name, int price) {
        ProductEntity oldProduct = productDao.selectProductById(id);
        oldProduct.setName(name);
        oldProduct.setPrice(price);
        productDao.updateProduct(oldProduct);
    }

}
