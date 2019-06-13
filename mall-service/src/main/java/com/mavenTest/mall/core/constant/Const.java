package com.mavenTest.mall.core.constant;

public class Const {
	//user表里面的两个角色
	//使用接口替代枚举的功能
	public interface Role {
		int ROLE_ADMIN = 0;  
		int ROLE_USER = 0;  
	}
	
	public interface CartChecked {
		int UNCHECKED = 0;  
		int CHECKED = 1;  
	}
	
	public static final String CURRENT_USER = "CURRENT_USER";
}
