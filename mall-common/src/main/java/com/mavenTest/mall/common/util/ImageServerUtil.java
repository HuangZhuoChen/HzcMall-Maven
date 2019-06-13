package com.mavenTest.mall.common.util;

public class ImageServerUtil {
	private static final String IMG_SERVER = "img.server";
	private static final String IMG_SERVER_LOCAL = "local";
	private static final String IMG_SERVER_QINIU = "qiniu";
	
	public static final String LOCAL_SERVER = "local.server";
	
	public enum EnumImageServer{
		LOCAL, QINIU
	}
	
	public static EnumImageServer getImageServer() {
		String server = PropertiesUtil.getProperty(IMG_SERVER);
		if (IMG_SERVER_QINIU.equals(server)) {
			return EnumImageServer.QINIU;
		} else {
			return EnumImageServer.LOCAL;
		}
	}

	public static Object getLocalUrl(String fileName) {
		String localServer = PropertiesUtil.getProperty(LOCAL_SERVER);
		// /pic/xxx.png
		return localServer + "/" + fileName;
	}
	
}
