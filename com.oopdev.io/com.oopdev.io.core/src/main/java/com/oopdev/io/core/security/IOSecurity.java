package com.oopdev.io.core.security;

import java.util.Map;

/**
 * 
 * @author kamilbukum
 *
 */

public interface IOSecurity{
	/**
	 * 
	 * @param headerMap
	 * @return
	 */
	public boolean login(Map<String,String> headerMap);
	/**
	 * 
	 * @return
	 */
	public boolean isSessionExist();
}
