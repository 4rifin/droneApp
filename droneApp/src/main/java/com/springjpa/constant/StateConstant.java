package com.springjpa.constant;

public enum StateConstant {
	IDLE("idle", 1), 
	LOADING("loading", 2), 
	LOADED("loaded", 3),
	DELIVERING("delivering", 4), 
	DELIVERED("delivered", 5),
	RETURNING("returning", 6); 
	
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
	private StateConstant(String labelKey, int code) {
		this.labelKey = labelKey;
		this.code = code;
	}
	public static String getLabelFromCode(int code) {
		for (StateConstant status : values()) {
			if (status.code == code) {
				return status.labelKey;
			}
		}
		return null;
	}
	
}
