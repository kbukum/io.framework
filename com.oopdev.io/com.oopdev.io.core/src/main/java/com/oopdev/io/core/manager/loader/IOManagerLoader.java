package com.oopdev.io.core.manager.loader;

import java.util.List;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.core.manager.IOAbstractManager;
import com.oopdev.io.core.manager.IOManagerMap;
import com.oopdev.io.core.util.IOLoaderUtility;
import com.oopdev.io.util.common.IOTypeUtil;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.logger.IOLogger;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOManagerLoader {
	/**
	 * 
	 */
	static IOLogger logger=IOLogger.getIOLogger(IOCoreConstants.IO_CORE_LOG_PATH,IOManagerLoader.class.getName());
	@SuppressWarnings("unchecked")
	
	/**
	 * 
	 * @throws IOUtilException
	 */
	/**
	 * 
	 * @throws IOUtilException
	 */
	public static void loadManagers() throws IOUtilException{
		logger.info("com.oopdev.io.core.manager.loader.IOManagerLoader.loadManagers.init");
		
		List<Class<?>> managerClassList=IOLoaderUtility.getClassListByAbstract(IOCoreConstants.IO_GROUP_PACKAGE_NAME, IOAbstractManager.class);
		for (Class<?> class1 : managerClassList) {
			IOAbstractManager abstractManager=IOTypeUtil.createClass((Class<? extends IOAbstractManager>)class1);
			if(abstractManager!=null){
				IOManagerMap.addManager(abstractManager);
				logger.info("com.oopdev.io.core.manager.loader.IOManagerLoader.loadManagers.manager.added",abstractManager.getClass().getName());
			}
		}
		logger.info("com.oopdev.io.core.manager.loader.IOManagerLoader.loadManagers.inited");
	}

}
