package com.oopdev.io.core.security;

import com.oopdev.io.core.component.loader.IOComponentMap;
import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.core.manager.IOAbstractManager;
import com.oopdev.io.util.validators.IOTypeValidator;
import com.oopdev.io.util.validators.IOValueValidator;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOSecurityManager extends IOAbstractManager{

	@Override
	public boolean isComponent(Class<?> clazz) {
		return clazz!=null&&IOTypeValidator.isHaveInterface(IOSecurity.class, clazz);
	}

	@Override
	public IOClass createComponent(String className,Class<?> clazz) throws IOCoreException {
		if(clazz==null||IOValueValidator.isEmpty(className))
			return null;
		if(IOComponentMap.getComponentclassmap().get(IOSecurityManager.class.getName()).get(className)!=null){
			throw new IOCoreException("com.oopdev.io.security.manager.IOSecurityManager.duplicate.security.class");
		}
		IOClass ioClass=new IOClass(className, clazz);
		return ioClass;
	}
}
