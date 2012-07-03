package com.oopdev.io.json.service.app;

import com.google.gson.Gson;

/**
 * 
 * @author kamilbukum
 *
 */
public class JsonResult {

	private final Object result;
	private final JsonFaultResult fault;
	private final String toString;
	public JsonResult(Object result,JsonFaultResult fault){
		this.result=result;
		this.fault=fault;
		this.toString=(new Gson()).toJson(this);
	}
	public JsonResult(Object result,String code,String message){
		this(result,new JsonFaultResult(code, message));
	}
	public Object getResult() {
		return result;
	}

	@Override
	public String toString() {
		return this.toString;
	}
	public JsonFaultResult getFault() {
		return fault;
	}
}
