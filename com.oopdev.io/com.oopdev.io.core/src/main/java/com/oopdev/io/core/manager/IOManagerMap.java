package com.oopdev.io.core.manager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.oopdev.io.core.component.loader.IOComponentMap;
import com.oopdev.io.core.component.loader.IOScopeType;
import com.oopdev.io.core.component.reflects.pojo.IOClass;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOManagerMap {
	private static final Map<String, IOAbstractManager> frameworkManagersMap=new LinkedHashMap<String, IOAbstractManager>();
	/**
	 * 
	 * @param abstractManager
	 */
	public static final void addManager(IOAbstractManager abstractManager){
		if(abstractManager!=null){
			String managerName=abstractManager.getClass().getName();
			if(!frameworkManagersMap.containsKey(managerName)&&!IOComponentMap.getComponentclassmap().containsKey(managerName)){
				frameworkManagersMap.put(managerName, abstractManager);
				IOComponentMap.getComponentclassmap().put(managerName, new LinkedHashMap<String, IOClass>());
				IOComponentMap.getComponentscopemap().put(managerName,  new LinkedHashMap<String, IOScopeType>());
			}
		}
	}
	/**
	 * 
	 * @param className
	 * @return
	 */
	public static final IOAbstractManager getManager(String className){
		return frameworkManagersMap.get(className);
	}
	/**
	 * 
	 * @return
	 */
	public static final Set<String> getManagerKeyList(){
		return frameworkManagersMap.keySet();
	}
}
