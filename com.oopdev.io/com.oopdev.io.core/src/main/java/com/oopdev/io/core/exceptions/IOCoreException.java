package com.oopdev.io.core.exceptions;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOCoreException extends IOUtilException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7147087887644796618L;

	/**
	 * 
	 * @param errorKey
	 */
	public IOCoreException(String errorKey) {
		super(IOCoreConstants.IO_CORE_EXCEPTION_PATH,errorKey);
	}

	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOCoreException(String errorKey,Object[] params) {
		super(IOCoreConstants.IO_CORE_EXCEPTION_PATH,errorKey,params);
	}

	/**
	 * 
	 * @param errorKey
	 */
	public IOCoreException(String errorKey,Throwable cause) {
		super(IOCoreConstants.IO_CORE_EXCEPTION_PATH,errorKey,cause);
	}

	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOCoreException(String errorKey,Object[] params,Throwable cause) {
		super(IOCoreConstants.IO_CORE_EXCEPTION_PATH,errorKey,params,cause);
	}


	/**
	 * 
	 * @param bundlePath
	 * @param errorKey 
	 */
	public IOCoreException(String bundlePath,String errorKey) {
		super(bundlePath,errorKey);
	}
	/**
	 * 
	 * @param bundlePath
	 * @param errorKey
	 * @param params
	 */
	public IOCoreException(String bundlePath,String errorKey,Object[] params) {
		super(bundlePath,errorKey,params);
	}

	/**
	 * 
	 * @param bundlePath
	 * @param errorKey 
	 */
	public IOCoreException(String bundlePath,String errorKey,Throwable cause) {
		super(bundlePath,errorKey,cause);
	}

	/**
	 * 
	 * @param bundlePath
	 * @param errorKey
	 * @param params
	 */
	public IOCoreException(String bundlePath,String errorKey,Object[] params,Throwable cause) {
		super(bundlePath,errorKey,params,cause);
	}
}
