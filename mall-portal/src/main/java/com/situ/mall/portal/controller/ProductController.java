package com.situ.mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping("/getProductListPage")
	public String getProductListPage(Integer categoryId, Model model) {
		//
		return "product_list";
	}
}
