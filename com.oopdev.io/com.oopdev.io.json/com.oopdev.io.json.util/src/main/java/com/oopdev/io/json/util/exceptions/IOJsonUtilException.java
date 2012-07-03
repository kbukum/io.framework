package com.oopdev.io.json.util.exceptions;

import com.oopdev.io.json.util.app.IOJsonUtilConstants;
import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonUtilException extends IOUtilException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4175941160122657357L;

	/**
	 * 
	 * @param errorKey
	 */
	public IOJsonUtilException(String errorKey) {
		super(IOJsonUtilConstants.IO_JSON_UTIL_EXCEPTION_PATH,errorKey);
	}

	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOJsonUtilException(String errorKey,Object[] params) {
		super(IOJsonUtilConstants.IO_JSON_UTIL_EXCEPTION_PATH,errorKey,params);
	}

	/**
	 * 
	 * @param errorKey
	 */
	public IOJsonUtilException(String errorKey,Throwable cause) {
		super(IOJsonUtilConstants.IO_JSON_UTIL_EXCEPTION_PATH,errorKey,cause);
	}

	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOJsonUtilException(String errorKey,Object[] params,Throwable cause) {
		super(IOJsonUtilConstants.IO_JSON_UTIL_EXCEPTION_PATH,errorKey,params,cause);
	}


}
