package com.oopdev.io.core.component.loader;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.core.bean.support.IOBeanSupportManager;
import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.core.manager.IOAbstractManager;
import com.oopdev.io.core.manager.IOManagerMap;
import com.oopdev.io.core.manager.loader.IOManagerLoader;
import com.oopdev.io.core.util.IOComponentUtility;
import com.oopdev.io.core.util.IOLoaderUtility;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.logger.IOLogger;
/**
 * 
 * @author kamilbukum
 *
 */
public class IOComponentLoader {
	
	private static IOLogger logger=IOLogger.getIOLogger(IOCoreConstants.IO_CORE_LOG_PATH,IOComponentLoader.class);
	/**
	 * 
	 * @param packageNames
	 * @throws IOUtilException
	 */
	public static void loadComponents(String packageNames) throws IOUtilException{
		
		// Each IO Framework have a manager for component initializing...
		logger.info("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.io.manager.initializing");
		// Uygulamanin Desteklediği Bean Frameworkleri aliniyor.
		
		IOBeanSupportManager.loadBeanSupports();
		IOManagerLoader.loadManagers();
		logger.info("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.named.component.finding");
		List<Class<?>> namedClassList=IOLoaderUtility.getClassListByAnnotation(packageNames,Named.class);
		logger.info("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.named.component.finded",namedClassList.size());
		
		for (Class<?> clazz : namedClassList) {
			for (String managerKey : IOManagerMap.getManagerKeyList()) {
				IOAbstractManager abstractManager=IOManagerMap.getManager(managerKey);
				if(IOComponentUtility.isCanBeComponent(abstractManager, clazz)){
					Named named=clazz.getAnnotation(Named.class);
					Map<String, IOClass> componentMap=IOComponentMap.getComponentclassmap().get(abstractManager.getClass().getName());
					Map<String, IOScopeType> scopeMap=IOComponentMap.getComponentscopemap().get(abstractManager.getClass().getName());
					if(!componentMap.containsKey(named.value())){
						componentMap.put(named.value(), abstractManager.createComponent(named.value(),clazz));
						IOScopeType ioScopeType=IOComponentUtility.getScope(IOComponentUtility.getCDIScope(clazz));
						scopeMap.put(named.value(),ioScopeType);
						logger.info("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.component.added",named.value());
					}else{
						IOClass ioClass=componentMap.get(named.value());
						if(!ioClass.getClazz().equals(clazz)){
							throw new IOCoreException("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.duplicate.named.error",new Object[]{named.value(),ioClass.getClazz().getName(),clazz.getName()});
						}else{
							logger.warn("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.component.already.added",named.value());
						}
					}
				}
			}
		}
	}
}
