package com.oopdev.io.core.component.reflects;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;

import com.oopdev.io.core.component.reflects.pojo.IOMethod;
import com.oopdev.io.core.component.reflects.pojo.IOParameter;
import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.util.validators.IOValueValidator;
/**
 * 
 * @author kamilbukum
 *
 */
public class IOComponentReflect {

	/**
	 * 
	 * @param methodMap
	 * @return
	 * @throws IOCoreException
	 */
	public static Map<String, IOMethod> createIOMethodsForClass(Map<String, Method> methodMap) throws IOCoreException{
		Map<String, IOMethod> ioMethodMap=new LinkedHashMap<String, IOMethod>();
		for (Entry<String, Method> entry : methodMap.entrySet()) {
			ioMethodMap.put(entry.getKey(),createIOMethod(entry.getKey(), entry.getValue()));
		}
		return ioMethodMap;
	}
	/**
	 * 
	 * @param methodName
	 * @param method
	 * @return
	 * @throws IOCoreException
	 */
	public static IOMethod createIOMethod(String methodName,Method method) throws IOCoreException{
		Map<String,IOParameter> parameters=getIOMethodParams(method);
		IOParameter ioParameter=new IOParameter(methodName, method.getReturnType());
		IOMethod ioMethod=new IOMethod(methodName, method, ioParameter);
		ioMethod.getParameterMap().putAll(parameters);
		return ioMethod;
	}
	/**
	 * 
	 * @param method
	 * @return
	 * @throws IOCoreException
	 */
	public static Map<String,IOParameter> getIOMethodParams(Method method) throws IOCoreException{
		if(method==null){
			throw new IOCoreException("com.oopdev.io.core.component.reflects.IOComponentReflect.getIOMethodParams.method.is.null");
		}
		Map<String,IOParameter> parameters=new LinkedHashMap<String,IOParameter>();
		List<String> parameterNames=new LinkedList<String>();
		for (int c=0;c<method.getParameterAnnotations().length;c++) {
			Named named=null;
			Annotation[] anns=method.getParameterAnnotations()[c];
			if(anns!=null&&anns.length>0){
				for(int i=0;i<anns.length;i++){
					if(anns[i]!=null&&anns[i].annotationType().equals(Named.class)){
						named=(Named)anns[i];
						break;
					}
				}
			}
			if(named==null||IOValueValidator.isEmpty(named.value())){
				throw new IOCoreException("com.oopdev.io.core.component.reflects.IOComponentReflect.getIOMethodParams.parameter.is.empty",new Object[]{method.getClass().getName(),method.getName()});
			}
			if(parameterNames.contains(named.value())){
				throw new IOCoreException("com.oopdev.io.core.component.reflects.IOComponentReflect.getIOMethodParams.parameter.duplicate.name",new Object[]{method.getClass().getName(),method.getName()});
			}
			parameterNames.add(named.value());
			IOParameter ioParameter=new IOParameter(named.value(), method.getParameterTypes()[c]);
			parameters.put(named.value(),ioParameter);
		}
		return parameters;
	}
}
