package com.example.practice;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;


@Dao
@ConfigAutowireable
public interface ProductDao {

    // データベースからデータを取得
    @Select
    List<ProductEntity> selectAll();

    // 特定のレコードを取得
    @Select
    ProductEntity selectProductById(Integer id);

    // データベースに追加
    @Insert(excludeNull = true)
    int insertProduct(ProductEntity product);

    // データベースを更新
    @Update
    int updateProduct(ProductEntity product);
}



