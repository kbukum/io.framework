package com.oopdev.io.util.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOTypeUtil {

	/**
	 * 
	 * @param clazz
	 * @param paramMap
	 * @return
	 * @throws IOUtilException
	 */
	public static  <E> E createClass(Class<? extends E> clazz,Map<Object, Class<?>> paramMap) throws IOUtilException{
		if(clazz==null)
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.createClass.class.is.null");
		try {

			if(paramMap!=null&&paramMap.size()>0){
				Class<?>[] parameterTypes = paramMap.values().toArray(new Class<?>[]{});
				Object params=paramMap.keySet().toArray();
				return clazz.getDeclaredConstructor(parameterTypes).newInstance(params);
			}
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (IllegalAccessException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (IllegalArgumentException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (SecurityException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (InvocationTargetException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (NoSuchMethodException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		}
	}
	public static  <E> E createClass(Class<? extends E> clazz) throws IOUtilException{
		return createClass(clazz, null);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <O> O genericTypeifStringThenTrim(O obj){
		if(obj==null)
			return null;
		if(obj instanceof String){
			obj=(O)((String) obj).trim();
			return obj;
		}
		return obj;
	}
	/**
	 * 
	 * @param obj
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <G,B> Class<G> getTypeParameterClass(B obj,int param) throws IOUtilException{
			if(obj==null){
				throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.getTypeParameterClass.obj.is.null");
			}
			if(obj.getClass().getSuperclass().getTypeParameters().length==0){
				throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.getTypeParameterClass.obj.superclass.genericttype.lengt.is.zero",new Object[]{obj.getClass().getName(),obj.getClass().getSuperclass().getName(),param});
			}
			if(param>=obj.getClass().getSuperclass().getTypeParameters().length){
				throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.getTypeParameterClass.obj.generictype.above.param",new Object[]{obj.getClass().getName(),obj.getClass().getSuperclass().getName(),param});
			}
			
			Type type = obj.getClass().getGenericSuperclass();
			ParameterizedType paramType = (ParameterizedType) type;
			return (Class<G>) paramType.getActualTypeArguments()[param];
	}
	
	/**
	 * 
	 * @param createClass
	 * @return
	 * @throws IOUtilException 
	 */
	public static <E> E newInstance(Class<E> createClass) throws IOUtilException{
		try {
			return createClass.newInstance();
		} catch (InstantiationException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (IllegalAccessException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		}
	}
	/**
	 * 
	 * @param oClazz
	 * @return
	 * @throws IOUtilException 
	 */
	public static <O> O returnNew(Class<O> oClazz) throws IOUtilException {
		try {
			return oClazz.newInstance();
		} catch (InstantiationException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		} catch (IllegalAccessException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		}catch (NullPointerException e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		}catch (Exception e) {
			throw new IOUtilException("com.oopdev.io.util.common.IOTypeUtil.exception",new Object[]{e.getMessage()},e);
		}
	}
}
