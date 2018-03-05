package com.situ.mall.core.service;

import java.util.List;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Category;

public interface ICategoryService {

	ServerResponse selectTopCategory();

	ServerResponse selectSecondCategory(Integer topCategoryId);

	Integer selectParentCategoryId(Integer categoryId);

	List<Category> selectTopCategoryList();

	List<Category> selectSecondCategoryList();

}
