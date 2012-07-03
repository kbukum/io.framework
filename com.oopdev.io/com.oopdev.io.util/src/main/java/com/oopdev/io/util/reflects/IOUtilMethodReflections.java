package com.oopdev.io.util.reflects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOUtilMethodReflections {

	public static Object invokeMethod(Method method,Object methodParent,Object ... args)throws IOUtilException{
		if(method==null){
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilMethodReflections.invokeMethod.method.is.null");
		}else if(methodParent==null){
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilMethodReflections.invokeMethod.method.parent.is.null");
		}else if(method.getParameterTypes().length>0&&(args==null||args.length!=method.getParameterTypes().length)){
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilMethodReflections.invokeMethod.method.is.null");
		}
		try {
			return (args==null||args.length==0)?method.invoke(methodParent):method.invoke(methodParent, args);
		} catch (IllegalArgumentException e) {
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilMethodReflections.exception",new Object[]{methodParent.getClass().getName(),method.getName()},e);
		} catch (IllegalAccessException e) {
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilMethodReflections.exception",new Object[]{methodParent.getClass().getName(),method.getName()},e);
		} catch (InvocationTargetException e) {
			throw new IOUtilException("com.oopdev.io.util.reflects.IOUtilMethodReflections.exception",new Object[]{methodParent.getClass().getName(),method.getName()},e);
		}
	}
	
	public static boolean isSetterMethod(Method method){
		if(method==null)
		   return false;
		// Method donusu void olmamali ve method parametre giris sayısı 1 olmali ve method baslangici set olmali
		return !method.getReturnType().equals(void.class)&&method.getParameterTypes()!=null||method.getParameterTypes().length==1&&method.getName().startsWith("set");
	}
	public static boolean isGetterMethod(Method method){
		if(method==null)
		   return false;
		String name=method.getName();
		
		// Method donusu void olmali ve giris parametresi olmamali !
		boolean condition=method.getReturnType().equals(void.class)&&(method.getParameterTypes()==null||method.getParameterTypes().length==0);
		// Method baslangici get olmali veya is ise donusu boolena olmali
		condition=name.startsWith("get")||(name.startsWith("is")&&method.getReturnType().equals(boolean.class));
		return condition;
	}
//	public static String getMethodUniqueNameInClass(Method method){
//		if(method==null)
//			return null;
//		Class<?>[] parameterTypes=method.getParameterTypes();
//		String methodParama
//		if(parameterTypes!=null){
//			for (Class<?> clazz : parameterTypes) {
//				clazz.getName();
//			}
//		}
//	}
}
