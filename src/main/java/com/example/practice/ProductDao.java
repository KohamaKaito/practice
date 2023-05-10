package com.example.practice;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.data.repository.query.Param;


@Dao
@ConfigAutowireable
public interface ProductDao {

    @Select
    List<ProductEntity> selectAll();

}