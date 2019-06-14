package com.mavenTest.mall.core.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.support.DaoSupport;

import com.mavenTest.mall.core.entity.User;

/*//帮助我们创建IOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//加载Spring的核心配置文件
@ContextConfiguration("classpath:applicationContext.xml")*/
public class UserMapperTest {

	@Test
	public void test() {
		User user = new User();
		user.setId(12);
		
		user.setEmail("3434");
		
		
	}

}
