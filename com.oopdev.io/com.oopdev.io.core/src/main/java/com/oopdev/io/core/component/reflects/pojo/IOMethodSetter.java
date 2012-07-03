package com.oopdev.io.core.component.reflects.pojo;

import java.lang.reflect.Method;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOMethodSetter extends IOAbstractMethod{

	/**
	 * 
	 * @param methodName
	 * @param method
	 * @param ioParameter
	 */
	public IOMethodSetter(String methodName,Method method,IOParameter ioParameter) {
		super(methodName,method,ioParameter);
	}

}
