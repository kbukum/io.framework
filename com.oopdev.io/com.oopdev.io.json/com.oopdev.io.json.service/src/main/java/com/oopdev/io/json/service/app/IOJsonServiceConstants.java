package com.oopdev.io.json.service.app;

import java.util.regex.Pattern;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonServiceConstants {
	public static final String IO_JSON_SERVICE_EXCEPTION_PATH="io-json-service-exceptions";
	public static final String IO_JSON_SERVICE_PACKAGE_NAME="com.oopdev.io.json.service";
	public static final String IO_JSON_SERVICE_PATH_REGEX="[/]([a-zA-Z-0-9_]{0,50})[/]([a-zA-Z-0-9_]{0,50})";
	public static final String IO_JSON_HEADER_METHOD_TYPE_KEY="METHOD_TYPE";
	public static final String IO_JSON_HEADER_FILE_TYPE_KEY="FILE_TYPE";
	public static final String IO_JSON_HEADER_FILE_TYPE_VALUE="application/json";
	public static final String IO_JSON_HEADER_ENCODING_TYPE_KEY="ENCODING_TYPE";
	/**
	 * File extension contants
	 */
	public static final String FILE_EXTENSION_CONTEXT_PARAM_NAME="IO_JSON_SERVICE_EXTENSION";
	public static final String FILE_EXTENSION_PATTERN="[a-zA-Z-0-9_]{0,10}";
	public static final String DEFAULT_FILE_EXTENSION="json";

	public static void main(String[] args) {
		System.out.println(Pattern.matches(IO_JSON_SERVICE_PATH_REGEX, "Denem_e.hjghjgh"));
	}
}
