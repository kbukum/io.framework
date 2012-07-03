package com.oopdev.io.util.generic;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.oopdev.io.util.validators.IOValueValidator;

public class IOQueryUtil {
	


	@SuppressWarnings("deprecation")
	public static Map<String, String> queryStringToMap(String queryString) {
		Map<String, String> map=new LinkedHashMap<String, String>();
		if(IOValueValidator.isEmpty(queryString))
			return map;
		for (String pair : queryString.split("&")) {
			int eq = pair.indexOf("=");
			if (eq < 0) {
				map.put(URLDecoder.decode(pair), "");
			} else {
				map.put(URLDecoder.decode(pair.substring(0, eq)), URLDecoder.decode(pair.substring(eq + 1)));
			}
		}
		return map;
	}
	@SuppressWarnings("deprecation")
	public static String queryStringToJson(String queryString){
		if(IOValueValidator.isEmpty(queryString))
			return "{}";
		String jsonData="";
		Map<String,String> queryMap=new LinkedHashMap<String, String>();
		
		for (String pair : queryString.split("&")) {
			int eq = pair.indexOf("=");
			if (eq < 0) {
				// key with no value
				jsonData="\""+URLDecoder.decode(pair)+"\""+":"+"\""+"\" ,";
			} else {
				// key=value
				String key = URLDecoder.decode(pair.substring(0, eq));
				String value = URLDecoder.decode(pair.substring(eq + 1));
				if(!IOValueValidator.isEmpty(key)){
				jsonData+="\""+key.trim()+"\""+":"+"\""+value.trim()+"\" ,";
				}
			}
			
		}
		jsonData=jsonData.substring(0,jsonData.length()-1);
		return "{"+jsonData+"}";
	}
	public static String complexQueryToJson(String queryString){
		Map<String, String> map=new LinkedHashMap<String, String>();
		if(IOValueValidator.isEmpty(queryString))
			return "{ }";
		for (String pair : queryString.split("&")) {
			int eq = pair.indexOf("=");
			if (eq < 0) {
				String key = URLDecoder.decode(pair.substring(0, eq));
			} else {
				String key = URLDecoder.decode(pair.substring(0, eq));
				String value = URLDecoder.decode(pair.substring(eq + 1));
				map.put(key, URLDecoder.decode(pair.substring(eq + 1)));
			}
		}
		return "";
	}
	
	
	public static String createJsonData(Map<String, String> map,int level){
		String data="";
		for (Entry<String, String> entry : map.entrySet()) {
			String[] values=entry.getKey().split(".");
			String result="";
			if(values.length>level){
				
				String deger=values[level];
				result=" { "+deger+" :";
				if(values.length>(level+1)){
					result+=createJsonData(map, level+1);
				}
				result+=" }";
			}
			data+=result;
		}
		data+=data.substring(0,data.length()-1);
		return data;
	}
	public static void main(String[] args) {
		String val1="1com.oopdev.io.xls";
		String val2="com.oopdev.io.io";
		String val3="com.oopdev.io.deneme";
		Map<String, String> valMap=new HashMap<String, String>();
		valMap.put(val1, "val1");
		valMap.put(val2, "val2");
		valMap.put(val3, "val3");
		
		String data=createJsonData(valMap, 0);
		System.out.println(data);
	}
}
