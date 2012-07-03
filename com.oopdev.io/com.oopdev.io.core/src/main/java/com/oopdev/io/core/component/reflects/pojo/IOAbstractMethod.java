package com.oopdev.io.core.component.reflects.pojo;

import java.lang.reflect.Method;

/**
 * 
 * @author kamilbukum
 *
 */
public abstract class IOAbstractMethod extends IOMethod{
	private IOField field;
	/**
	 * 
	 * @param methodName
	 * @param method
	 * @param ioParameter
	 */
	public IOAbstractMethod(String methodName,Method method,IOParameter ioParameter) {
		super(methodName, method,ioParameter);
	}
	/**
	 * 
	 * @return
	 */
	public IOField getField() {
		return field;
	}
	/**
	 * 
	 * @param field
	 */
	public void setField(IOField field) {
		this.field = field;
	}

}
