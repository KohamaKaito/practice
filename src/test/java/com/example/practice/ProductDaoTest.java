package com.example.practice;

import com.example.practice.ProductDao;
import com.example.practice.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDaoTest {

    @Mock
    private ProductDao productDao;

    @Test
    public void testSelectAll() {
        // モックの動作を設定
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(new ProductEntity(1, "Product 1", 1000));
        productList.add(new ProductEntity(2, "Product 2", 2000));
        when(productDao.selectAll()).thenReturn(productList);

        // メソッドの呼び出し
        List<ProductEntity> result = productDao.selectAll();

        // 結果の検証
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals(1000, result.get(0).getPrice());
        assertEquals(2, result.get(1).getId());
        assertEquals("Product 2", result.get(1).getName());
        assertEquals(2000, result.get(1).getPrice());
    }

    @Test
    public void testSelectProductById() {
        // モックの動作を設定
        ProductEntity product = new ProductEntity(1, "Product 1", 1000);
        when(productDao.selectProductById(1)).thenReturn(product);

        // メソッドの呼び出し
        ProductEntity result = productDao.selectProductById(1);

        // 結果の検証
        assertEquals(1, result.getId());
        assertEquals("Product 1", result.getName());
        assertEquals(1000, result.getPrice());
    }

    @Test
    public void testInsertProduct() {
        // モックの動作を設定
        ProductEntity product = new ProductEntity(1, "Product 1", 1000);
        when(productDao.insertProduct(product)).thenReturn(1);

        // メソッドの呼び出し
        int result = productDao.insertProduct(product);

        // 結果の検証
        assertEquals(1, result);
    }

    @Test
    public void testUpdateProduct() {
        // モックの動作を設定
        ProductEntity product = new ProductEntity(1, "Product 1", 1000);
        when(productDao.updateProduct(product)).thenReturn(1);

        // メソッドの呼び出し
        int result = productDao.updateProduct(product);

        // 結果の検証
        assertEquals(1, result);
    }

    @Test
    public void testDeleteProduct() {
        // モックの動作を設定
        when(productDao.deleteProduct(1)).thenReturn(1);

        // メソッドの呼び出し
        int result = productDao.deleteProduct(1);

        // 結果の検証
        assertEquals(1, result);
    }
}