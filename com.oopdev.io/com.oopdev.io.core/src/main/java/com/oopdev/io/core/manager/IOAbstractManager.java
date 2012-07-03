package com.oopdev.io.core.manager;

import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.exceptions.IOCoreException;

/**
 * 
 * @author kamilbukum
 *
 */
public abstract class IOAbstractManager {
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public abstract boolean isComponent(Class<?> clazz);
	/**
	 * 
	 * @param className
	 * @param clazz
	 * @return
	 * @throws IOCoreException
	 */
	public abstract IOClass createComponent(String className,Class<?> clazz) throws IOCoreException;
}
