package com.oopdev.io.json.service.app;

/**
 * 
 * @author kamilbukum
 *
 */
public class JsonFaultResult {

	private String code;
	private String message;
	
	public JsonFaultResult(String code,String message) {
		this.code=code;
		this.message=message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
