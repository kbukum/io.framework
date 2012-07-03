package com.oopdev.io.core.bean.support;

import com.oopdev.io.core.component.loader.IOComponentMap;
import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.util.exceptions.IOUtilException;


/**
 * 
 * @author kamilbukum
 *
 */
public abstract class IOBeanManager implements Comparable<IOBeanManager>{
	private int priority;
	protected boolean isEnabled=false;
	/**
	 * 
	 */
	public IOBeanManager() {
	}
	/**
	 * 
	 * @param priority
	 */
	public IOBeanManager(int priority) {
		this.priority=priority;
	}
	
	
	@Override
	public int compareTo(IOBeanManager o) {
		if(o==null)
			return 0;
		return getPriority()==o.getPriority()?0:priority>o.getPriority()?1:-1;
	}
	@Override
	public String toString() {
		return "Bean Manager-"+priority;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return isEnabled;
	}
	/**
	 * 
	 * @param projectId
	 * @param ioClass
	 * @return
	 * @throws IOUtilException
	 */
	public Object getComponent(String projectId,IOClass ioClass) throws IOUtilException{
		if(ioClass==null){
			 throw new IOCoreException("com.oopdev.io.core.bean.support.IOBeanManager.getComponent.ioClass.is.null",new  Object[]{projectId});
		}
		return getComponent(projectId, ioClass.getClassName(), ioClass.getClazz());
	}
	/**
	 * 
	 * @param projectId
	 * @param name
	 * @return
	 * @throws IOUtilException
	 */
	public Object getComponent(String projectId,String name) throws IOUtilException{
		IOClass ioClass=IOComponentMap.getComponentclassmap().get(projectId).get(name);
		return getComponent(projectId, ioClass);
	}
	/**
	 * 
	 * @param projectId
	 * @param name
	 * @param clazz
	 * @return
	 * @throws IOUtilException
	 */
	public abstract Object getComponent(String projectId,String name,Class<?> clazz) throws IOUtilException;
	/**
	 * 
	 * @return
	 */
	public int getPriority() {
		return priority;
	}
}
