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

	public boolean addItem(CartItemVo cartItemVo, Integer stock) {
		boolean isExist = false;
		// 1、将要加入购物车的商品productId和amount插入cookie
		// 1.2 这个商品cookie里面没有，创建然后插入
		for (CartItemVo item : cartItemVos) {
			// 1.1 这个商品cookie里面已经有了，根据productId找到这件商品，更新数量即可
			if (item.getProduct().getId().intValue() == cartItemVo.getProduct().getId().intValue()) {
				isExist = true;
				//这个商品新的数量=原来购物车中这个商品数量+新添加这个商品的数量
				int amount = item.getAmount() + cartItemVo.getAmount();
				//判断商品数量有没有超过库存
				if (amount <= stock) {
					//没有超过库存将这个产品数量跟新到购物车里面
					item.setAmount(amount);
					return true;
				} else {
					return false;
				}
			}
		}
		//在原来的购物车中就没有这件商品，直接添加
		if (isExist == false) {
			cartItemVos.add(cartItemVo);
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Integer i1 = 128;
		Integer i2 = 128;
		if (i1.intValue() == i2.intValue()) {
			System.out.println("true");
		}
		
	}

}
