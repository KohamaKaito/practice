package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * ProductServiceクラス
 * ビジネスロジックを担うクラス
 * コントローラーから渡されたデータを受け取り、
 * そのデータに基づいてビジネスロジックを実行し、
 * DAOを介してデータベースにアクセスする
 *
 * @author kohama
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * selectProductメソッド
     * データベースから商品一覧を取得するメソッド
     *
     * @return 商品一覧が格納されたリスト
     */
    public List<ProductEntity> selectProduct() {
        List<ProductEntity> productList = productDao.selectAll();
        return productList;
    }

    /**
     * insertProductメソッド
     * データベースのデータを追加するメソッド
     *
     * @param name  追加したい名前
     * @param price 追加したい価格
     */
    public void insertProduct(String name, int price) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        productDao.insertProduct(product);
    }

    /**
     * updateProductメソッド
     * データベースのデータを更新するメソッド
     *
     * @param id    更新対象のid
     * @param name  更新したい名前
     * @param price 更新したい価格
     */
    @Transactional
    public void updateProduct(int id, String name, int price) {
        ProductEntity productEntity = productDao.selectProductById(id);
        // 例外処理
        if (productEntity == null) {
        }
        // entityに値をセット
        setUpdateEntity(productEntity, name, price);
        // 更新処理
        productDao.updateProduct(productEntity);
    }


    /**
     * deleteProductメソッド
     * データベースにデータを削除するメソッド
     *
     * @param id 削除対象のid
     */
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }


    /**
     * setUpdateEntityメソッド
     * 更新対象のEntityのフィールド値を変更するメソッド
     *
     * @param productEntity 更新対象のEnity
     * @param name          新しいname
     * @param price         新しいprice
     */
    private void setUpdateEntity(ProductEntity productEntity, String name, int price) {
        productEntity.setName(name);
        productEntity.setPrice(price);
    }

}
