package com.situ.mall.portal.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.naming.directory.InvalidSearchControlsException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.omg.CORBA.TRANSACTION_REQUIRED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.mall.common.response.ServerResponse;
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
	public String getCartPage(HttpServletRequest request, Model model) {
		// 将cookie里面所有的商品转换成CartVo这个对象
		CartVo cartVo = getCartVoFromCookie(request);
		if (cartVo != null) {
			List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
			//从Cookie里面转换成的CartVo对象里面的product对象只有id属性，
			//所以要将其他属性填充上
			for (CartItemVo item : cartItemVos) {
				Product product = productService.selectById(item.getProduct().getId());
				item.setProduct(product);
			}
			model.addAttribute("cartVo", cartVo);
		}

		return "cart";
	}

	@RequestMapping("/addOrUpdateCart")
	@ResponseBody
	public ServerResponse addOrUpdateCart(Integer productId, Integer amount, Boolean isChecked,
			HttpServletRequest request,
			HttpServletResponse response) {
		// 讲Cookie里面的购物车转换为CartVo对象
		CartVo cartVo = getCartVoFromCookie(request);
		// 原来cookie中没有购物车，所以转换为的CartVo是null。
		if (cartVo == null) {
			cartVo = new CartVo();
		}

		boolean result = addOrUpdateCarVo(productId, amount, isChecked, cartVo);
		if (result == false) {
			return ServerResponse.createError("添加购物车失败");
		}
		
		//将CartVo对象设置到Cookie中
		setCartVoToCookie(response, cartVo);
		return ServerResponse.createSuccess("添加购物车成功");
	}
	
	@RequestMapping("/delCartItemById")
	@ResponseBody
	public ServerResponse delCartItemById(Integer productId, HttpServletRequest request,
			HttpServletResponse response) {
		// 讲Cookie里面的购物车转换为CartVo对象
		CartVo cartVo = getCartVoFromCookie(request);
		if (cartVo == null) {
			return ServerResponse.createError("获取购物车失败");
		}
		
		//遍历删除指定id的购物项
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		Iterator<CartItemVo> iterator = cartItemVos.iterator();
		while (iterator.hasNext()) {
			CartItemVo cartItemVo = iterator.next();
			if (productId.intValue() == cartItemVo.getProduct().getId().intValue()) {
				iterator.remove();
			}
		}

		//将CartVo对象设置到Cookie中
		setCartVoToCookie(response, cartVo);
		return ServerResponse.createSuccess("删除购物车成功");
	}

	private boolean addOrUpdateCarVo(Integer productId, Integer amount, Boolean isChecked, CartVo cartVo) {
		Product productTemp = productService.selectById(productId);
		boolean isExist = false;
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		for (CartItemVo item : cartItemVos) {
			// 1.1 这个商品cookie里面已经有了，根据productId找到这件商品，更新数量即可
			if (item.getProduct().getId().intValue() == productId.intValue()) {
				isExist = true;
				if (amount != null) {
					//这个商品新的数量=原来购物车中这个商品数量+新添加这个商品的数量
					int newAmount = item.getAmount() + amount;
					//判断商品数量有没有超过库存
					if (newAmount > productTemp.getStock()) {
						//超过库存
						return false;
					} 
					item.setAmount(newAmount);
				}
				//跟新是否选中状态
				if (isChecked != null) {
					if (isChecked) {
						item.setIsChecked(Const.CartChecked.CHECKED);
					} else {
						item.setIsChecked(Const.CartChecked.UNCHECKED);
					}
				}
				return true;//更新完这个商品数量后，后面的就不需要遍历
			}
		}
		//在原来的购物车中就没有这件商品，直接添加
		if (isExist == false) {
			CartItemVo cartItemVo = new CartItemVo();
			Product product = new Product();
			product.setId(productId);
			cartItemVo.setProduct(product);
			cartItemVo.setIsChecked(Const.CartChecked.CHECKED);
			cartItemVo.setAmount(amount);
			
			cartItemVos.add(cartItemVo);
			return true;
		}
		return false;
	}

	/**
	 * 将Cookie中的购物车信息转换为CartVo对象
	 * @param request
	 * @return
	 */
	private CartVo getCartVoFromCookie(HttpServletRequest request) {
		CartVo cartVo = null;
		ObjectMapper objectMapper = new ObjectMapper();
		// 只有对象中不为null才转换
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 将客户端中购物车cookie拿出来
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (CART_COOKIE.equals(cookie.getName())) {// 找到了客户端cookie中购物车信息
					String value = cookie.getValue();
					try {
						// 将json字符串转换为对象
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
		return cartVo;
	}

	/**
	 * 将CartVo对象设置到Cookie中
	 * @param response
	 * @param cartVo
	 */
	private void setCartVoToCookie(HttpServletResponse response, CartVo cartVo) {
		ObjectMapper objectMapper = new ObjectMapper();
		// 只有对象中不为null才转换
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 将cartVo对象以json形式放到cookie
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

		// 将购物车json放到cookie
		Cookie cookie = new Cookie(CART_COOKIE, stringWriter.toString());
		// 设置cookie的存储时间
		cookie.setMaxAge(60 * 60 * 24);// 单位秒
		// 设置cookie路径
		cookie.setPath("/");
		// 将cookie发送到浏览器
		response.addCookie(cookie);
	}
}
