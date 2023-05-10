package com.example.practice;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import java.util.List;

@Dao
@ConfigAutowireable
public interface ProductDao {

    @Select
    List<ProductEntity> selectAll();
}
