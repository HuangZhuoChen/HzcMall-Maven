package com.situ.mall.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.situ.mall.common.util.ImageServerUtil;
import com.situ.mall.common.util.PropertiesUtil;
import com.situ.mall.common.util.ImageServerUtil.EnumImageServer;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyServletContextListener.contextInitialized()");
		String imageServer = "";
		if (ImageServerUtil.getImageServer() == EnumImageServer.QINIU) {
			imageServer = PropertiesUtil.getProperty("qiniu.server");
		} else {
			imageServer = PropertiesUtil.getProperty("local.server");
		}
		System.out.println(imageServer);
		//放到ServletContext域对象中
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("imageServer", imageServer);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
