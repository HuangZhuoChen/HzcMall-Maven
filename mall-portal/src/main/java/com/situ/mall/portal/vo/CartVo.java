package com.situ.mall.portal.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.parsing.FailFastProblemReporter;

public class CartVo {
	// 购物车里面购物项集合
	private List<CartItemVo> cartItemVos = new ArrayList<>();

	public List<CartItemVo> getCartItemVos() {
		return cartItemVos;
	}

	public void setCartItemVos(List<CartItemVo> cartItemVos) {
		this.cartItemVos = cartItemVos;
	}

	public static void main(String[] args) {
		Integer i1 = 128;
		Integer i2 = 128;
		if (i1.intValue() == i2.intValue()) {
			System.out.println("true");
		}
		
	}

}
