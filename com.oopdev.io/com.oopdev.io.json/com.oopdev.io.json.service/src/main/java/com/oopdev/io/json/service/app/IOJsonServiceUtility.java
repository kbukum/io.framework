package com.oopdev.io.json.service.app;

import java.util.Map;

import com.google.gson.JsonObject;
import com.oopdev.io.json.service.exceptions.IOJsonServiceException;
import com.oopdev.io.json.util.common.IOJsonUtility;
import com.oopdev.io.json.util.exceptions.IOJsonUtilException;
import com.oopdev.io.util.validators.IOValueValidator;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonServiceUtility {

	static String queryStringRegEx="[a-z";
	public static Map<String, String> getHeaderControl(){
		return null;
	}
	public static JsonObject serviceHeaderController(String header) throws IOJsonServiceException, IOJsonUtilException{
		if(IOValueValidator.isEmpty(header)){
			throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonServiceUtility.header.is.not.found");
		}
		
		JsonObject jsonObj = IOJsonUtility.jsonToMap(header);
		if(header==null){
			throw new IOJsonServiceException("com.oopdev.io.json.service.web.servlet.IOJsonServiceUtility.header.is.not.json");
		}
		return jsonObj;
	}
	public static String getJsonDataWithResult(String code,String message,String jsonData){
		String result= "" +
				"{  \n" +
				"	result:" +jsonData==null?"{}":jsonData+"\n" +
				",	fault:{\n" +
				"		code:" +code+"\n" +
				"	,	message:" +message+"\n" +
				"	} " +
				"}";
		return result;
	}
	
	
//	public static String requestQueryStringToJson(String queryString){
//		
//	}
}
