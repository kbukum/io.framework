package com.oopdev.io.core.bean.support;

import com.oopdev.io.core.app.IOCoreConstants;
import com.oopdev.io.core.app.IOProjectType;
import com.oopdev.io.core.bean.create.IOBeanWebCreator;
import com.oopdev.io.util.common.IOTypeUtil;
import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOBeanManagerImpl extends IOBeanManager{
	
	public IOBeanManagerImpl() {
		super(-1);
		isEnabled=true;
	}
	@Override
	public Object getComponent(String projectId, String name, Class<?> clazz)throws IOUtilException {
		if(IOProjectType.WEB==IOCoreConstants.getIOProjectType()){
			return IOBeanWebCreator.getComponent(projectId, name, clazz);
		}
		return  IOTypeUtil.newInstance(clazz);
	}
}
