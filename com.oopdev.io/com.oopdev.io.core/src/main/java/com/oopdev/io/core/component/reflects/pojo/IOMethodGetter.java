package com.oopdev.io.core.component.reflects.pojo;

import java.lang.reflect.Method;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOMethodGetter extends IOAbstractMethod{

	/**
	 * 
	 * @param methodName
	 * @param method
	 * @param ioParameter
	 */
	public IOMethodGetter(String methodName,Method method,IOParameter ioParameter) {
		super(methodName,method,ioParameter);
	}
}
