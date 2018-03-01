package com.situ.mall.common.response;

import java.io.Serializable;

public class ServerResponse<T> implements Serializable {
	//当前状态（程序员判断状态）:成功、失败、未登录、没有权限
	private Integer code;
	//描述信息（主要是给用户看的提示信息）
	private String msg;
	//总数量
	private Integer count;
	//后台返回给前端的数据
	private T data;

	private ServerResponse() {
	}

	private ServerResponse(Integer code) {
		this.code = code;
	}

	private ServerResponse(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private ServerResponse(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private ServerResponse(Integer code, String msg, Integer count, T data) {
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	//只是告诉前台：成功这种状态
	public static <T> ServerResponse<T> createSuccess() {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
	}

	//告诉前台：code,msg
	public static <T> ServerResponse<T> createSuccess(String msg) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg);
	}

	//告诉前台：code,msg,data
	public static <T> ServerResponse<T> createSuccess(String msg, T data) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	//告诉前台：code,msg,count,data
	public static <T> ServerResponse<T> createSuccess(String msg, Integer count, T data) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg, count, data);
	}

	//只是告诉前台：失败这种状态
	public static <T> ServerResponse<T> createError() {
		return new ServerResponse<>(ResponseCode.ERROR.getCode());
	}

	//告诉前台：code,msg
	public static <T> ServerResponse<T> createError(String msg) {
		return new ServerResponse<>(ResponseCode.ERROR.getCode(), msg);
	}

	//告诉前台：code,msg,data
	public static <T> ServerResponse<T> createError(String msg, T data) {
		return new ServerResponse<>(ResponseCode.ERROR.getCode(), msg, data);
	}

	public boolean isSuccess() {
		return code == ResponseCode.SUCCESS.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public static void main(String[] args) {
		System.out.println(ResponseCode.ERROR.getCode());
	}
}
