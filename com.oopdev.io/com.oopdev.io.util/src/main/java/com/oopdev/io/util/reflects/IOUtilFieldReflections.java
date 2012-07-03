package com.oopdev.io.util.reflects;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOUtilFieldReflections {

	/**
	 * 
	 * @param field
	 * @param fieldParent
	 * @param fieldGetterMethod
	 * @return
	 * @throws IOUtilException
	 */
	public static Object getFieldValue(Field field,Object fieldParent,Method fieldGetterMethod) throws IOUtilException{
		
		Object value=null;
		if(fieldGetterMethod!=null){
			try {
				value=fieldGetterMethod.invoke(fieldParent);
			} catch (IllegalArgumentException e) {
				throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilFieldReflections.exception",new Object[]{e.getMessage()},e);
			} catch (IllegalAccessException e) {
				throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilFieldReflections.exception",new Object[]{e.getMessage()},e);
			} catch (InvocationTargetException e) {
				throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilFieldReflections.exception",new Object[]{e.getMessage()},e);
			}
		}else{
				boolean accessible=field.isAccessible();
				if(!accessible){
				field.setAccessible(true);
				value=getPublicFieldValue(field, fieldParent);
				field.setAccessible(accessible);
				}else{
					value=getPublicFieldValue(field, fieldParent);
				}
		}
		return value;
	}
	/**
	 * 
	 * @param field
	 * @param fieldParent
	 * @return
	 * @throws IOUtilException
	 */
	public static Object getPublicFieldValue(Field field,Object fieldParent)throws IOUtilException{
		Object value=null;
		try {
			value=field.get(fieldParent);
		} catch (IllegalArgumentException e) {
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilFieldReflections.exception",new Object[]{e.getMessage()},e);
		} catch (IllegalAccessException e) {
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilFieldReflections.exception",new Object[]{e.getMessage()},e);
		}
		return value;
	}
}
