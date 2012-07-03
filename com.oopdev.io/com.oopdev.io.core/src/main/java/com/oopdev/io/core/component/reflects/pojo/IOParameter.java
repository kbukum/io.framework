package com.oopdev.io.core.component.reflects.pojo;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOParameter {
	private final String name;
	private final Class<?> type;
	/**
	 * 
	 * @param name
	 * @param type
	 */
	public IOParameter(String name,Class<?> type) {
		this.name=name;
		this.type=type;
	}
	
	public String getName() {
		return name;
	}
	public Class<?> getType() {
		return type;
	}
}
