package com.oopdev.io.json.service.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import com.oopdev.io.json.util.exceptions.IOJsonUtilException;
import com.oopdev.io.util.validators.IOValueValidator;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonWebReader {

	private final HttpServletRequest request;
	private String data;
	private String header;
	private final DoType doType;
	public IOJsonWebReader(HttpServletRequest request,DoType doType) throws IOJsonUtilException {
		this.doType=doType;
		this.request=request;		
	}
	
	public String getJsonDataFromRequest(){
		this.data=request.getParameter("IO_JSON_DATA")!=null?request.getParameter("IO_JSON_DATA"):"";
		this.header=request.getHeader("IO_JSON_HEADER");
		if(IOValueValidator.isEmpty(this.header))
			this.header=request.getParameter("IO_JSON_HEADER");
		if(!IOValueValidator.isEmpty(this.data)){
			return this.data;
		}
		
		switch (doType) {
		case GET:
				data=IOQueryUtil.queryStringToJson(request.getQueryString());
			break;
		case POST:
		case PUT:
		case DELETE:
		case OPTIONS:
		case TRACE:
		case HEAD:
			String reader=readJsonData(request);
			data=IOQueryUtil.queryStringToJson(reader);
		break;
		}	
		return this.data;
	}
	
	private String readJsonData(HttpServletRequest request){
		String jsonData=null;
		try {
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line + "\n");
				line = reader.readLine();
			}
			reader.close();
			jsonData = sb.toString();
			jsonData=URLDecoder.decode(jsonData, "UTF-8");
		} catch (IOException e2) {
			return null;
		}
		return jsonData;
	}
	public String getData() {
		return data;
	}
	public DoType getDoType() {
		return doType;
	}
	public String getHeader() {
		return header;
	}
}
