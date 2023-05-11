package com.example.practice;

import java.util.List;

import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;


@Dao
@ConfigAutowireable
public interface ProductDao {

    // データベースからすべてのレコードを取得
    @Select
    List<ProductEntity> selectAll();

    // 特定のレコードを取得
    @Select
    ProductEntity selectProductById(Integer id);

    // データベースのレコードを追加
    @Insert(excludeNull = true)
    int insertProduct(ProductEntity product);

    // データベースのレコードを更新
    @Update
    int updateProduct(ProductEntity product);

    // データベースのレコードを削除
    @Delete
    int deleteProduct(ProductEntity product);
}



