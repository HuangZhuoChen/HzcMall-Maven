package com.situ.mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index() {
		//1、把一级分类查出来，放到域对象
		//topCategoryList
		//2、把所有的二级分类查出来，放到域对象中
		//secondCategoryList
		return "index";
	}
}
