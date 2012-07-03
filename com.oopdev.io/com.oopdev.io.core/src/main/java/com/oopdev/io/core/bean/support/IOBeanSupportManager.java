package com.oopdev.io.core.bean.support;

import java.util.Collections;
import java.util.List;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.core.util.IOLoaderUtility;
import com.oopdev.io.util.common.IOTypeUtil;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.logger.IOLogger;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOBeanSupportManager {
	
	static IOLogger logger=IOLogger.getIOLogger(IOCoreConstants.IO_CORE_LOG_PATH,IOBeanSupportManager.class);
	
	@SuppressWarnings("unchecked")
	
	/**
	 * 
	 * @throws IOUtilException
	 */
	public static void loadBeanSupports() throws IOUtilException{
		logger.info("com.oopdev.io.core.bean.support.IOBeanSupportManager.loadBeanSupports.configuration");
		
		List<Class<?>> supportManagerClassList=IOLoaderUtility.getClassListByAbstract(IOCoreConstants.IO_GROUP_PACKAGE_NAME, IOBeanManager.class);
		IOBeanSupportMap.getBeanmanagermap().clear();
		for (Class<?> class1 : supportManagerClassList) {
			try{
				IOBeanManager beanSupportManager=IOTypeUtil.createClass((Class<? extends IOBeanManager>)class1);
				if(beanSupportManager.isEnabled()){
					IOBeanSupportMap.getBeanmanagermap().add(beanSupportManager);
				}
			}catch (Throwable e) {
				logger.warn("com.oopdev.io.core.bean.support.IOBeanSupportManager.loadBeanSupports.not.support",class1.getName());
			}
		}
		Collections.sort(IOBeanSupportMap.getBeanmanagermap(),Collections.reverseOrder());
		logger.info("com.oopdev.io.core.bean.support.IOBeanSupportManager.loadBeanSupports.configurated");
	}
}
