package com.situ.mall.core.service;

import java.util.List;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Product;

public interface IProductService {

	ServerResponse<List<Product>> pageList(Integer page, Integer limit, Product product);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Product product);

	Product selectById(Integer id);

	ServerResponse update(Product product);

}
