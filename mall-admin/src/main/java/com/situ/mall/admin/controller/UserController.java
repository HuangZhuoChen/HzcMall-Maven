package com.situ.mall.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/getLoginPage")
	public String getLoginPage() {
		return "login";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, User user) {
		return userService.pageList(page, limit, user);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return userService.deleteById(id);
	}
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return userService.deleteAll(ids);
	}
	
	@RequestMapping("/getUserPage")
	public String getUserPage() {
		return "user_list";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse login(String username, String password, HttpSession session) {
		ServerResponse<User> response = userService.login(username, password);
		if (response.isSuccess()) {
			User user = response.getData();
			if (user.getRole() == Const.Role.ROLE_ADMIN) {
				//说明登录的确实是管理员
				session.setAttribute(Const.CURRENT_USER, user);
				return response;
			} else {
				return ServerResponse.createError("不是管理员，无法登录");
			}
		}
		return response;
	}
}
