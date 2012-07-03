package com.oopdev.io.json.service.component.manager;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.oopdev.io.core.component.reflects.IOComponentReflect;
import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.component.reflects.pojo.IOMethod;
import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.core.manager.IOAbstractManager;
import com.oopdev.io.json.service.annotations.IOJsonMethod;
import com.oopdev.io.json.service.annotations.IOJsonService;
import com.oopdev.io.json.service.app.IOJsonServiceConstants;
import com.oopdev.io.json.service.exceptions.IOJsonServiceException;
import com.oopdev.io.util.reflects.IOUtilReflect;
import com.oopdev.io.util.validators.IOValueValidator;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOJsonServiceComponentManager extends IOAbstractManager{

	@Override
	public boolean isComponent(Class<?> clazz) {
		return clazz!=null&&clazz.getAnnotation(IOJsonService.class)!=null;
	}

	@Override
	public IOClass createComponent(String className,Class<?> clazz) throws IOCoreException {
		if(clazz==null||IOValueValidator.isEmpty(className))
			return null;
		IOClass ioClass=new IOClass(className, clazz);
		List<Method> methodList=IOUtilReflect.getMethodByAnnotation(IOJsonMethod.class, clazz);	
		Map<String, Method> methodMap=new LinkedHashMap<String, Method>();
		for (Method method : methodList) {
			IOJsonMethod ioJsonMethod=method.getAnnotation(IOJsonMethod.class);
			if(IOValueValidator.isEmpty(ioJsonMethod.value())){
//				throw new IOJsonServiceException("Method name cannot be empty on method="+method.getName()+" in class="+clazz.getName());
				throw new IOJsonServiceException(IOJsonServiceConstants.IO_JSON_SERVICE_PACKAGE_NAME+".component.manager.IOJsonServiceComponentManager_method_name_is_null",new Object[]{method.getName(),clazz.getName()});
			}
			if(methodMap.containsKey(ioJsonMethod.value())){
//				throw new IOJsonServiceException("Duplicate method name="+ioJsonMethod.value()+" on methods="+method.getName()+","+methodMap.get(ioJsonMethod.value()).getName()+" in a  class="+clazz.getName());
				throw new IOJsonServiceException(IOJsonServiceConstants.IO_JSON_SERVICE_PACKAGE_NAME+".component.manager.IOJsonServiceComponentManager_method_name_is_duplicate",new Object[]{ioJsonMethod.value(),method.getName(),methodMap.get(ioJsonMethod.value()).getName(),clazz.getName()});
			}
			methodMap.put(ioJsonMethod.value(), method);
		}
		Map<String, IOMethod> ioMethodMap = IOComponentReflect.createIOMethodsForClass(methodMap);
		ioClass.getIoMethodMap().putAll(ioMethodMap);
		return ioClass;
	}
}
