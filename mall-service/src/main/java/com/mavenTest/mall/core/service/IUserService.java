package com.mavenTest.mall.core.service;

import java.util.List;

import com.mavenTest.mall.common.response.ServerResponse;
import com.mavenTest.mall.core.entity.User;

public interface IUserService {

	ServerResponse<User> login(String username, String password);

	ServerResponse<List<User>> pageList(Integer page, Integer limit, User user);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

}
