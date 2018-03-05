package com.situ.mall.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Category;
import com.situ.mall.core.mapper.CategoryMapper;
import com.situ.mall.core.service.ICategoryService;
@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public ServerResponse selectTopCategory() {
		List<Category> list = categoryMapper.selectTopCategory();
		if (list == null || list.size() == 0) {
			return ServerResponse.createError("没有一级分类");
		}
		
		return ServerResponse.createSuccess("查找一级分类成功", list);
	}

	@Override
	public ServerResponse selectSecondCategory(Integer topCategoryId) {
		List<Category> list = categoryMapper.selectSecondCategory(topCategoryId);
		if (list == null || list.size() == 0) {
			return ServerResponse.createError("没有二级分类");
		}
		
		return ServerResponse.createSuccess("查找二级分类成功", list);
	}

	@Override
	public Integer selectParentCategoryId(Integer categoryId) {
		return categoryMapper.selectParentCategoryId(categoryId);
	}

	@Override
	public List<Category> selectTopCategoryList() {
		return categoryMapper.selectTopCategory();
	}

	@Override
	public List<Category> selectSecondCategoryList() {
		return categoryMapper.selectSecondCategoryList();
	}
		
}
