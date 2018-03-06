package com.situ.mall.portal.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.Product;
import com.situ.mall.core.service.IProductService;
import com.situ.mall.portal.vo.CartItemVo;
import com.situ.mall.portal.vo.CartVo;

@Controller
@RequestMapping("/cart")
public class CartController {
	private String CART_COOKIE = "cart_cookie";

	@Autowired
	private IProductService productService;
	
	
	@RequestMapping("/getCartPage")
	public String getCartPage(Integer productId, Integer amount, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("productId: " + productId);
		System.out.println("amount: " + amount);
		
		ObjectMapper objectMapper = new ObjectMapper();
		//只有对象中不为null才转换
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		CartVo cartVo = null;
		//将客户端中购物车cookie拿出来
		//springmvc
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (CART_COOKIE.equals(cookie.getName())) {//找到了客户端cookie中购物车信息
					String value = cookie.getValue();
					try {
						//将json字符串转换为对象
						cartVo = objectMapper.readValue(value, CartVo.class);
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if (cartVo == null) {
			cartVo = new CartVo();
		}
		
		if (null != productId) {
			CartItemVo cartItemVo = new CartItemVo();
			Product productTemp = productService.selectById(productId);
			Product product = new Product();
			product.setId(productId);
			product.setStock(productTemp.getStock());
			cartItemVo.setProduct(product);
			cartItemVo.setIsChecked(Const.CartChecked.UNCHECKED);
			cartItemVo.setAmount(amount);
			cartVo.addItem(cartItemVo);
			
			//将cartVo对象以json形式放到cookie
			StringWriter stringWriter = new StringWriter();
			try {
				objectMapper.writeValue(stringWriter, cartVo);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//将购物车json放到cookie
			Cookie cookie = new Cookie(CART_COOKIE, stringWriter.toString());
			//设置cookie的存储时间
			cookie.setMaxAge(60 * 60 * 24);//单位秒
			//设置cookie路径
			cookie.setPath("/");
			//将cookie发送到浏览器
			response.addCookie(cookie);
		}
		
		//2、将cookie里面所有的商品查询出来，转换成Cart这个对象
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		for (CartItemVo item : cartItemVos) {
			Product product = productService.selectById(item.getProduct().getId());
			item.setProduct(product);
		}
		//3、将Cart对象放到域对象中
		model.addAttribute("cartVo", cartVo);
		
		return "cart";
	}
}
