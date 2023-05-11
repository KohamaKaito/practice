package com.example.practice;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.data.repository.query.Param;


@Dao
@ConfigAutowireable
public interface ProductDao {

    // データベースからデータを取得
    @Select
    List<ProductEntity> selectAll();

    // データベースに追加
    @Insert(excludeNull = true)
    int insert(ProductEntity product);
}



