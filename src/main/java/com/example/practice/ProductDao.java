package com.example.practice;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.Delete;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * ProductDaoインタフェース
 * データベースにアクセスするためのインタフェース
 * クエリを発行してデータの取得、更新、削除を担う
 *
 * @author kohama
 */
@Dao
@ConfigAutowireable
public interface ProductDao {

    /**
     * selectAllメソッド
     * productテーブルのすべてのレコードを取得するメソッド
     *
     * @return すべてのレコードのオブジェクトを格納した配列
     */
    @Select
    List<ProductEntity> selectAll();

    /**
     * selectProductByIdメソッド
     * 特定のレコードをIDで受け取るメソッド
     *
     * @param id 取得したいメソッド
     * @return レコードのオブジェクト
     */
    @Select
    ProductEntity selectProductById(Integer id);

    /**
     * insertProductメソッド
     * データベースのレコードを追加するメソッド
     *
     * @param product 追加したいオブジェクト
     * @return
     */
    @Insert(excludeNull = true)
    int insertProduct(ProductEntity product);

    /**
     * updateProductメソッド
     * データベースのレコードを更新するメソッド
     *
     * @param product 更新したいオブジェクト
     * @return
     */
    @Update
    int updateProduct(ProductEntity product);

    /**
     * deleteProductメソッド
     * データベースのレコードを削除するメソッド
     *
     * @param id 更新したいid
     * @return
     */
    @Delete(sqlFile = true)
    int deleteProduct(int id);
}



