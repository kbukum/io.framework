package com.oopdev.io.json.service.exceptions;

import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.json.service.app.IOJsonServiceConstants;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonServiceException extends IOCoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4175941160122657357L;

	/**
	 * 
	 * @param errorKey
	 */
	public IOJsonServiceException(String errorKey) {
		super(IOJsonServiceConstants.IO_JSON_SERVICE_EXCEPTION_PATH,errorKey);
	}

	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOJsonServiceException(String errorKey,Object[] params) {
		super(IOJsonServiceConstants.IO_JSON_SERVICE_EXCEPTION_PATH,errorKey,params);
	}

	/**
	 * 
	 * @param errorKey
	 */
	public IOJsonServiceException(String errorKey,Throwable cause) {
		super(IOJsonServiceConstants.IO_JSON_SERVICE_EXCEPTION_PATH,errorKey,cause);
	}

	/**
	 * 
	 * @param errorKey
	 * @param params
	 */
	public IOJsonServiceException(String errorKey,Object[] params,Throwable cause) {
		super(IOJsonServiceConstants.IO_JSON_SERVICE_EXCEPTION_PATH,errorKey,params,cause);
	}


}
