package com.oopdev.io.json.service.web.servlet;

/**
 * 
 * @author kamilbukum
 *
 */
public enum DoType {
	GET("GET"),POST("POST"),DELETE("DELETE"),HEAD("HEAD"),OPTIONS("OPTIONS"),PUT("PUT"),TRACE("TRACE");
	private String doType;
	
	private DoType(String doType){
		this.doType=doType;
	}

	public String getDoType() {
		return doType;
	}
	
	public static DoType getDoType(String doType){
		for (DoType value : DoType.values()) {
			if(value.getDoType().equalsIgnoreCase(doType)){
				return value;
			}
		}
		return null;
	}
}
