package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<ProductEntity> getProductList() {

        List<ProductEntity> productList = productDao.selectAll();

        return productList;
    }
}
