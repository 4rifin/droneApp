package com.springjpa.rest.model;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;

public class MessageInfo {
	@ApiModelProperty(notes="Message Info Code")
	private String code;
	@ApiModelProperty(notes="Message Info Status")
	private HttpStatus status;
	@ApiModelProperty(notes="Message Info Message Text")
	private String message;
	@ApiModelProperty(notes="Message Info Result Responese Api")
	private Object result;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
}
