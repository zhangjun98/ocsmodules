package com.zzq.common;

import java.io.Serializable;

public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

	
	private Integer code;
	

	private String message;
	
	
	
	
	private Object data;
	
	public BaseResult(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BaseResult(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public BaseResult(Integer code, String message,Integer count, Object data) {
		super();
		this.code = code;
		this.message = message;
		
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
