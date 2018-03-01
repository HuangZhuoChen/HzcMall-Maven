package com.situ.mall.core.mapper;

import java.util.List;

import com.situ.mall.core.entity.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> pageList(Product product);

	int deleteAll(String[] idsArray);
}