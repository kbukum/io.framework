package com.oopdev.io.core.app;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOCoreConstants {
	public static final String IO_CORE_EXCEPTION_PATH="io-core-exceptions";
	public static final String IO_CORE_LOG_PATH="io-core-logs";
	public static final String IO_GROUP_PACKAGE_NAME="com.oopdev.io";
	public static final String IO_CORE_PACKAGE_NAME="com.oopdev.io.core";
	public static final String IO_APPLICATION_PACKAGES_KEY="APP_PACKAGE_NAME";
	public static 		String IO_APPLICATION_PACKAGES_VALUE="";
	public static 	    String IO_CHARACTER_ENCODING_VALUE="UTF-8";
	public static final String IO_CHARACTER_ENCODING_KEY="IO_CHARACTER_ENCODING";
	public static final String IO_SECURITY_PACKAGE_NAME="com.oopdev.io.security.manager.IOSecurityManager";
	protected static    int	   IO_PROJECT_TYPE=IOProjectType.CONSOLE.getProjectType();
	public static IOProjectType  getIOProjectType() {
		return IOProjectType.getProjectType(IO_PROJECT_TYPE);
	}
}
