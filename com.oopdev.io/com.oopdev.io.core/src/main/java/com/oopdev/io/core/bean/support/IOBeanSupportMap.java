package com.oopdev.io.core.bean.support;

import java.util.LinkedList;
import java.util.List;

import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.exceptions.IOCoreException;
/**
 * 
 * @author kamilbukum
 *
 */
public class IOBeanSupportMap {
	private static final List<IOBeanManager> beanManagerMap=new LinkedList<IOBeanManager>();
	public static List<IOBeanManager> getBeanmanagermap() {
		return beanManagerMap;
	}
	/**
	 * 
	 * @param projectId
	 * @param ioClass
	 * @return
	 * @throws IOCoreException
	 */
	public static IOReturnBean getComponent(String projectId,IOClass ioClass) throws IOCoreException{
		for (IOBeanManager beanManager : beanManagerMap) {
			try{
				IOReturnBean bean=new IOReturnBean(beanManager.getComponent(projectId,ioClass),beanManager.getProjectUniqueName().name());
			if(bean.getObject()!=null){
				return bean;
			}
			}catch (Exception e) {
				throw new IOCoreException("com.oopdev.io.core.bean.support.IOBeanSupportMap.getComponent.exception",new Object[]{e.getMessage()},e);
			}
		}
		return null;
	}
}
