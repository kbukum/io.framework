package com.oopdev.io.util.exceptions;

import com.oopdev.io.util.app.IOUtilConstants;
import com.oopdev.io.util.bundles.IOUtilBundle;

/**
 * 
 * @author kamil bukum
 *
 */
public class IOUtilException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param errorKey
	 */
	public IOUtilException(String errorKey) {
		super(IOUtilBundle.getInstance(IOUtilConstants.IO_UTIL_EXCEPTION_PATH).getMessage(errorKey));
	}
	/**
	 * 
	 * @param bundlePath
	 * @param errorKey 
	 */
	public IOUtilException(String bundlePath,String errorKey) {
		super(IOUtilBundle.getInstance(bundlePath).getMessage(errorKey));
	}
	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOUtilException(String errorKey,Object[] params) {
		super(IOUtilBundle.getInstance(IOUtilConstants.IO_UTIL_EXCEPTION_PATH).getMessage(errorKey,params));
	}
	/**
	 * 
	 * @param bundlePath
	 * @param errorKey
	 * @param params
	 */
	public IOUtilException(String bundlePath,String errorKey,Object[] params) {
		super(IOUtilBundle.getInstance(bundlePath).getMessage(errorKey,params));
	}
	/**
	 * 
	 * @param errorKey
	 */
	public IOUtilException(String errorKey,Throwable cause) {
		super(IOUtilBundle.getInstance(IOUtilConstants.IO_UTIL_EXCEPTION_PATH).getMessage(errorKey),cause);
	}
	/**
	 * 
	 * @param bundlePath
	 * @param errorKey 
	 */
	public IOUtilException(String bundlePath,String errorKey,Throwable cause) {
		super(IOUtilBundle.getInstance(bundlePath).getMessage(errorKey),cause);
	}
	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOUtilException(String errorKey,Object[] params,Throwable cause) {
		super(IOUtilBundle.getInstance(IOUtilConstants.IO_UTIL_EXCEPTION_PATH).getMessage(errorKey,params),cause);
	}
	/**
	 * 
	 * @param bundlePath
	 * @param errorKey
	 * @param params
	 */
	public IOUtilException(String bundlePath,String errorKey,Object[] params,Throwable cause) {
		super(IOUtilBundle.getInstance(bundlePath).getMessage(errorKey,params),cause);
	}
}
