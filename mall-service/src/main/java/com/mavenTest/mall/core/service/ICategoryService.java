package com.mavenTest.mall.core.service;

import java.util.List;

import com.mavenTest.mall.common.response.ServerResponse;
import com.mavenTest.mall.core.entity.Category;

public interface ICategoryService {

	ServerResponse selectTopCategory();

	ServerResponse selectSecondCategory(Integer topCategoryId);

	Integer selectParentCategoryId(Integer categoryId);

	List<Category> selectTopCategoryList();

	List<Category> selectSecondCategoryList();

	ServerResponse getCategoryCountAnalysis();

}
