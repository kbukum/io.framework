package com.oopdev.io.core.component.loader;

/**
 * 
 * @author kamilbukum
 *
 */
public enum IOScopeType {
	NONE("NONE"),REQUEST("REQUEST"),CONVERSATION("CONVERSATION"),SESSION("SESSION"),APPLICATION("APPLICATION");
	
	private final String ioScopeType;
	
	private IOScopeType(String ioScopeType) {
		this.ioScopeType=ioScopeType;
	}
	public String getIoScopeType() {
		return ioScopeType;
	}
	
	@Override
	public String toString() {
		return this.ioScopeType;
	}
}
