package com.mavenTest.mall.core.service;

import java.util.List;

import com.mavenTest.mall.common.response.ServerResponse;
import com.mavenTest.mall.core.entity.Product;

public interface IProductService {

	ServerResponse<List<Product>> pageList(Integer page, Integer limit, Product product);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Product product);

	Product selectById(Integer id);

	ServerResponse update(Product product);

}
