package com.oopdev.io.core.component.reflects.pojo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOClass {
	private final String className;
	private final Class<?> clazz;
	private final Map<String, IOMethod> ioMethodMap=new LinkedHashMap<String, IOMethod>();
	private final Map<String, IOField> ioFieldMap=new LinkedHashMap<String, IOField>();
	/**
	 * 
	 * @param className
	 * @param clazz
	 */
	public IOClass(String className,Class<?> clazz) {
		this.className=className;
		this.clazz=clazz;
	}
	/**
	 * 
	 * @return
	 */
	public Map<String, IOMethod> getIoMethodMap() {
		return ioMethodMap;
	}
	/**
	 * 
	 * @return
	 */
	public Map<String, IOField> getIoFieldMap() {
		return ioFieldMap;
	}
	/**
	 * 
	 * @return
	 */
	public Class<?> getClazz() {
		return clazz;
	}
	/**
	 * 
	 * @return
	 */
	public String getClassName() {
		return className;
	}
}
