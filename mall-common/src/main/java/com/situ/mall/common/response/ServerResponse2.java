package com.situ.mall.common.response;

import java.io.Serializable;

public class ServerResponse2<T> implements Serializable {
	//当前状态（程序员判断状态）:成功、失败、未登录、没有权限
	private Integer status;
	//描述信息（主要是给用户看的提示信息）
	private String msg;
	//后台返回给前端的数据
	private T data;

	private ServerResponse2() {
	}

	private ServerResponse2(Integer status) {
		this.status = status;
	}

	private ServerResponse2(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private ServerResponse2(Integer status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	//只是告诉前台：成功这种状态
	public static <T> ServerResponse2<T> createSuccess() {
		return new ServerResponse2<>(ResponseCode.SUCCESS.getCode());
	}

	//告诉前台：status,msg
	public static <T> ServerResponse2<T> createSuccess(String msg) {
		return new ServerResponse2<>(ResponseCode.SUCCESS.getCode(), msg);
	}

	//告诉前台：status,msg,data
	public static <T> ServerResponse2<T> createSuccess(String msg, T data) {
		return new ServerResponse2<>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	//只是告诉前台：失败这种状态
	public static <T> ServerResponse2<T> createError() {
		return new ServerResponse2<>(ResponseCode.ERROR.getCode());
	}

	//告诉前台：status,msg
	public static <T> ServerResponse2<T> createError(String msg) {
		return new ServerResponse2<>(ResponseCode.ERROR.getCode(), msg);
	}

	//告诉前台：status,msg,data
	public static <T> ServerResponse2<T> createError(String msg, T data) {
		return new ServerResponse2<>(ResponseCode.ERROR.getCode(), msg, data);
	}

	public boolean isSuccess() {
		return status == ResponseCode.SUCCESS.getCode();
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static void main(String[] args) {
		System.out.println(ResponseCode.ERROR.getCode());
	}
}
