package com.oopdev.io.json.util.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oopdev.io.core.component.reflects.pojo.IOMethod;
import com.oopdev.io.core.component.reflects.pojo.IOParameter;
import com.oopdev.io.json.util.exceptions.IOJsonUtilException;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.validators.IOValueValidator;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonUtility {

	public static JsonObject jsonToMap(String json) throws IOJsonUtilException {
		try{
			JsonParser parse = new JsonParser();
			JsonObject jobj = (JsonObject) parse.parse(json);
			return jobj;
		}catch (Exception e) {
			throw new IOJsonUtilException("com.oopdev.io.json.util.common.IOJsonUtility.jsonToMap.json.parse.error",new Object[]{e.getMessage()},e);
		}
	}
	public static String mapToJson(Map<String,?> map){
		String data="";
			if(map==null||map.size()==0)
				return "{ }";
		for (Entry<String, ?> entry : map.entrySet()) {
			data+="\""+entry.getKey()+"\""+":"+"\""+entry.getValue()+"\""+"\n,";
		}
		 data=data.substring(0,data.length()-1);
		 
		 return "{\n"+ data +"\n}";
	}
	public static List<Object> jsonToPojos(String body,IOMethod ioMethod) throws IOUtilException{
		List<Object> objectList=new LinkedList<Object>();
		if(IOValueValidator.isEmpty(body))
			body="{}";
		JsonParser parse = new JsonParser();
		JsonObject jobj = (JsonObject) parse.parse(body);
		
		for(Entry<String, IOParameter> ent:ioMethod.getParameterMap().entrySet()){
			JsonElement element = jobj.get(ent.getKey());
			if(element!=null){
				objectList.add(new Gson().fromJson(element,ent.getValue().getType()));
			}else{
				objectList.add(null);
			}
		}
		return objectList;
	}	
	
	
	public static String pojoToJson(Object pojo){
		if(pojo==null||pojo.getClass().equals(void.class))
			return "{}";
		Gson gson=new Gson();
		return gson.toJson(pojo);
	}
}
