package com.mavenTest.mall.portal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mavenTest.mall.core.entity.Category;
import com.mavenTest.mall.core.service.ICategoryService;

@Controller
public class IndexController {
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		//1、把一级分类查出来，放到域对象
		//topCategoryList
		List<Category> topCategoryList = categoryService.selectTopCategoryList();
		model.addAttribute("topCategoryList", topCategoryList);
		//2、把所有的二级分类查出来，放到域对象中
		//secondCategoryList
		List<Category> secondCategoryList = categoryService.selectSecondCategoryList();
		model.addAttribute("secondCategoryList", secondCategoryList);
		return "index";
	}
}
