package com.example.practice;

import com.example.practice.ProductDao;
import com.example.practice.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService();
        productService.setProductDao(productDao);
    }

    @Test
    void testSelectProduct() {
        // モックの戻り値を設定
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(new ProductEntity(1, "Product 1", 100));
        productList.add(new ProductEntity(2, "Product 2", 200));
        when(productDao.selectAll()).thenReturn(productList);

        // テスト対象のメソッドを実行
        List<ProductEntity> result = productService.selectProduct();

        // 結果の検証
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals(100, result.get(0).getPrice());
        assertEquals("Product 2", result.get(1).getName());
        assertEquals(200, result.get(1).getPrice());

        // モックのメソッドが呼び出されたか検証
        verify(productDao, times(1)).selectAll();
    }

    @Test
    void testInsertProduct() {
        // テストデータの設定
        String name = "New Product";
        int price = 300;

        // テスト対象のメソッドを実行
        productService.insertProduct(name, price);

        // モックのメソッドが呼び出されたか検証
        verify(productDao, times(1)).insertProduct(any(ProductEntity.class));
    }

    @Test
    void testUpdateProduct() {
        // テストデータの設定
        int id = 1;
        String name = "Updated Product";
        int price = 400;
        ProductEntity existingProduct = new ProductEntity(id, "Old Product", 200);
        when(productDao.selectProductById(id)).thenReturn(existingProduct);

        // テスト対象のメソッドを実行
        productService.updateProduct(id, name, price);

        // 結果の検証
        assertEquals(name, existingProduct.getName());
        assertEquals(price, existingProduct.getPrice());

        // モックのメソッドが呼び出されたか検証
        verify(productDao, times(1)).selectProductById(id);
        verify(productDao, times(1)).updateProduct(existingProduct);
    }

    @Test
    void testDeleteProduct() {
        // テストデータの設定
        int id = 1;

        // テスト対象のメソッドを実行
        productService.deleteProduct(id);

        // モックのメソッドが呼び出されたか検証
        verify(productDao, times(1)).deleteProduct(id);
    }

    @Test
    void testExistsId() {
        // テストデータの設定
        int id = 1;
        ProductEntity existingProduct = new ProductEntity(id, "Existing Product", 200);
        when(productDao.selectProductById(id)).thenReturn(existingProduct);

        // テスト対象のメソッドを実行
        boolean result = productService.existsId(id);

        // 結果の検証
        assertTrue(result);

        // モックのメソッドが呼び出されたか検証
        verify(productDao, times(1)).selectProductById(id);
    }
}