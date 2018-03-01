package com.situ.mall.core.service;

import com.situ.mall.common.response.ServerResponse;

public interface ICategoryService {

	ServerResponse selectTopCategory();

	ServerResponse selectSecondCategory(Integer topCategoryId);

	Integer selectParentCategoryId(Integer categoryId);

}
