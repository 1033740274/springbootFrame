package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 10337
 *	自定义业务异常类
 */
@Getter
@Setter
public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;	
	
	/*
	 * @see org.springframework.http.httpStatus
	 */
	private Integer errCode;
	
	private Throwable cause;
	
	public BussinessException(String msg) {
		super(msg);
	}
	
	public BussinessException(String msg, Throwable cause) {
		super(msg);
		this.setCause(cause);
	}
	
	public BussinessException(Integer errCode, String msg, Throwable cause) {
		super(msg);
		this.setErrCode(errCode); 
		this.setCause(cause);
	}
}
