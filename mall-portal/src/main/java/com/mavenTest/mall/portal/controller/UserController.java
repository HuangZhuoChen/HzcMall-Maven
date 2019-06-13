package com.mavenTest.mall.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavenTest.mall.common.response.ServerResponse;
import com.mavenTest.mall.core.constant.Const;
import com.mavenTest.mall.core.entity.User;
import com.mavenTest.mall.core.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse login(String username, String password, HttpSession session) {
		ServerResponse<User> response = userService.login(username, password);
		User user = response.getData();
		session.setAttribute(Const.CURRENT_USER, user);
		return response;
	}
}
