package com.oopdev.io.core.component.loader;

import java.util.LinkedHashMap;
import java.util.Map;

import com.oopdev.io.core.component.reflects.pojo.IOClass;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOComponentMap {
	
	private static final Map<String, Map<String, IOClass>> componentClassMap=new LinkedHashMap<String, Map<String,IOClass>>();
	private static final Map<String, Map<String, IOScopeType>> componentScopeMap=new LinkedHashMap<String, Map<String,IOScopeType>>();
	private static final Map<String, Map<IOScopeType,Map<String, Object>>> componentMap=new LinkedHashMap<String, Map<IOScopeType,Map<String,Object>>>();
	public static Map<String, Map<String, IOClass>> getComponentclassmap() {
		return componentClassMap;
	}
	public static Map<String, Map<String, IOScopeType>> getComponentscopemap() {
		return componentScopeMap;
	}
	public static Map<String, Map<IOScopeType,Map<String, Object>>> getComponentmap() {
		return componentMap;
	}
}
