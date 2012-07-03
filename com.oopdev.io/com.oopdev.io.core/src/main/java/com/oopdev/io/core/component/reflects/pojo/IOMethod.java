package com.oopdev.io.core.component.reflects.pojo;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOMethod {
	private final String methodName;
	private final Method method;
	private final IOParameter ioParameter;
	private final Map<String, IOParameter> parameterMap=new LinkedHashMap<String, IOParameter>();
	/**
	 * 
	 * @param methodName
	 * @param method
	 * @param ioParameter
	 * <pre>
	 * Burada yapılan işlemler çok güzel işlemler
	 * </pre>
	 */
	public IOMethod(String methodName,Method method,IOParameter ioParameter) {
		this.methodName=methodName;
		this.method=method;
		this.ioParameter=ioParameter;
	}
	/**
	 * 
	 * @return
	 */
	public Method getMethod() {
		return method;
	}

	public String getMethodName() {
		return methodName;
	}

	public Map<String, IOParameter> getParameterMap() {
		return parameterMap;
	}

	public IOParameter getIoParameter() {
		return ioParameter;
	}
}
