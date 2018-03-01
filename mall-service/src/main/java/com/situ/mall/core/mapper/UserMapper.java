package com.situ.mall.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.mall.core.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	int checkUsername(String username);

	User selectLogin(@Param("username")String username, @Param("password")String password);

	List<User> pageList(User user);

	int deleteAll(String[] idsArray);
}