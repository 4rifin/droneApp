package com.springjpa.constant;

public enum StatusConstant {
	READY("ready", 1), 
	PROGRESS("progress", 2), 
	DONE("done", 3),
	RETURN("return", 4);
	
	String labelKey;
	int code;
	
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public static String getLabelFromCode(int code) {
		for (StatusConstant status : values()) {
			if (status.code == code) {
				return status.labelKey;
			}
		}
		return null;
	}
	private StatusConstant(String labelKey, int code) {
		this.labelKey = labelKey;
		this.code = code;
	}
	
	
}
