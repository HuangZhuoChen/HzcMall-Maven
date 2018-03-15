package com.situ.mall.portal.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.Order;
import com.situ.mall.core.entity.OrderItem;
import com.situ.mall.core.entity.Product;
import com.situ.mall.core.entity.Shipping;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.service.IProductService;
import com.situ.mall.portal.vo.CartItemVo;
import com.situ.mall.portal.vo.CartVo;

@Controller
@RequestMapping("/order")
public class OrderController {/*
	@Autowired
	private IShippingService shippingService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("/getOrderPage")
	public String getOrderPage(HttpSession session, Model model) {
		//1.从Session中获得user对象
		User user = (User) session.getAttribute("user");
		//2.通过user得到收获地址
		List<Shipping> shippings = shippingService.selectByUserId(user.getId());
		model.addAttribute("shippings", shippings);
		//3.将Cookie里面的购物车信息转换为CartVo对象
		CartVo cartVo = getCartVoFromCookie(request);
		//将CartItemVo里面的Product填满信息，因为现在只有一个id
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		Iterator<CartItemVo> iterator = cartItemVos.iterator();
		while (iterator.hasNext()) {
			CartItemVo item = iterator.next();
			if (item.getIsChecked() == Const.CartChecked.UNCHECKED) {
				iterator.remove();
			} else {
				Product product = productService.selectById(cartItemVo.getProduct().getId());
				cartItemVo.setProduct(product);
			}
		}
		//cartVo.setCartItemVos(cartItemVos);
		model.addAttribute("cartVo", cartVo);
		return "order";
	}
	
	@RequestMapping("/addOrder")
	@ResponseBody
	public ServerResponse addOrder(Integer shippingId,..) {
		//1.创建订单对象
		Order order = new Order();
		order.setOrderNo("201803121139234");//精确到毫秒
		order.setUserId(user.getId());//user从session得到
		order.setShippingId(shippingId);
		//....
		//2.将订单插入数据库
		orderService.add(order);
		
		//3.从Cookie里面得到购物车CartVo
		CartVo cartVo = getCartVoFromCookie(request);
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		for (CartItemVo item : cartItemVos) {
			//购物车里面被选中的才加入数据库
			if (item.getIsChecked() == Const.CartChecked.CHECKED) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderNo(order.getOrderNo());
				orderItem.setUserId(user.getId());
				Product product = productService.selectById(cartItemVo.getProduct().getId());
				orderItem.setProductId(cartItemVo.getId());
				orderItem.setCurrentUnitPrice(product.getPrice());
				orderItem.setProductName(product.getName());
				orderService.addOrderItem(orderItem);
			}
		}
		
		//4.遍历cartVo将所有isChecked是1的删除，然后再写到cookie
		Iterator<CartItemVo> iterator = cartItemVos.iterator();
		while (iterator.hasNext()) {
			CartItemVo item = iterator.next();
			if (item.getIsChecked() == Const.CartChecked.CHECKED) {
				iterator.remove();
			}
		}
		
		return ServerResponse;
	}
*/}
