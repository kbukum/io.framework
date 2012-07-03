package com.oopdev.io.core.util;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.oopdev.io.core.component.loader.IOComponentMap;
import com.oopdev.io.core.component.loader.IOScopeType;
import com.oopdev.io.core.component.reflects.pojo.IOClass;
import com.oopdev.io.core.exceptions.IOCoreException;
import com.oopdev.io.core.manager.IOAbstractManager;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOComponentUtility {

	/**
	 * 
	 * @param manager
	 * @param clazz
	 * @return
	 */
	public static boolean isCanBeComponent(IOAbstractManager manager,Class<?> clazz){
		if(clazz==null||manager==null){
			return false;
		}
		Named named=clazz.getAnnotation(Named.class);
		if(named==null||named.value()==null)
			return false;
		return manager.isComponent(clazz);
	}
	/**
	 * 
	 * @param manager
	 * @param clazz
	 * @return
	 * @throws IOCoreException
	 */
	public static boolean addIfItsComponent(IOAbstractManager manager,Class<?> clazz) throws IOCoreException{
		if(isCanBeComponent(manager, clazz)){
			return false;
		}
		Map<String, IOClass> componentMap = IOComponentMap.getComponentclassmap().get(manager.getClass().getName());
		Named named=clazz.getAnnotation(Named.class);
		String name=named.value();
		if(componentMap.containsKey(name)){
			IOClass ioClass=componentMap.get(name);
			if(!ioClass.getClazz().equals(clazz)){
				throw new IOCoreException("com.oopdev.io.core.component.loader.IOComponentLoader.loadComponents.duplicate.named.error",new Object[]{named.value(),ioClass.getClazz().getName(),clazz.getName()});
			}
		}else{
			IOClass ioClass= manager.createComponent(name, clazz);
			if(ioClass==null){
				return false;
			}
			componentMap.put(name,ioClass);
		}
		return true;
	}
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static Annotation getCDIScope(Class<?> clazz){
		if(clazz==null){
			return null;
		}else if(clazz.isAnnotationPresent(RequestScoped.class)){
			return clazz.getAnnotation(RequestScoped.class);
		}else if(clazz.isAnnotationPresent(ConversationScoped.class)){
			return clazz.getAnnotation(ConversationScoped.class);
		}else if(clazz.isAnnotationPresent(SessionScoped.class)){
			return clazz.getAnnotation(SessionScoped.class);
		}else if(clazz.isAnnotationPresent(ApplicationScoped.class)){
			return clazz.getAnnotation(ApplicationScoped.class);
		} 
		return null;
	}
	/**
	 * 
	 * @param ann
	 * @return
	 */
	public static IOScopeType getScope(Annotation ann){
		if(ann==null) {
			return IOScopeType.NONE;
		}else if(ann instanceof RequestScoped){
			return IOScopeType.REQUEST;
		}else if(ann instanceof ConversationScoped){
			return IOScopeType.CONVERSATION;
		}else if(ann instanceof SessionScoped){
			return IOScopeType.SESSION;
		}else if(ann instanceof ApplicationScoped){
			return IOScopeType.APPLICATION;
		}
		return IOScopeType.NONE;
	}
	
	/**
	 * 
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <E> E getComponent(String name,Class<?> clazz){
		return null;
	}
}
