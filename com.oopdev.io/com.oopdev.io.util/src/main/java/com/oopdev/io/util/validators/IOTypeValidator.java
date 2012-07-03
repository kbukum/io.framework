package com.oopdev.io.util.validators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.oopdev.io.util.common.IOTypeUtil;
import com.oopdev.io.util.exceptions.IOUtilException;
import com.oopdev.io.util.reflects.IOUtilReflect;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOTypeValidator {


	/**
	 * 
	 * @param clazz
	 * @return
	 * Byte control
	 */
	public static boolean isByte(Class<?> clazz) {
		return (clazz != null && (clazz.equals(byte.class) || clazz.equals(Byte.class)));
	}

	public static boolean isShort(Class<?> clazz) {
		
		return (clazz != null && (clazz.equals(short.class) || clazz
				.equals(Short.class)));
	}

	public static boolean isInteger(Class<?> clazz) {
		return (clazz != null && (clazz.equals(int.class) || clazz
				.equals(Integer.class)));
	}

	public static boolean isLong(Class<?> clazz) {
		return (clazz != null && (clazz.equals(long.class) || clazz
				.equals(Long.class)));
	}

	public static boolean isFloat(Class<?> clazz) {
		return (clazz != null && (clazz.equals(float.class) || clazz.equals(Float.class)));
	}

	public static boolean isDouble(Class<?> clazz) {
		return (clazz != null && (clazz.equals(double.class) || clazz.equals(Double.class)));
	}

	public static boolean isChar(Class<?> clazz) {
		return (clazz != null && clazz.equals(char.class));
	}

	public static boolean isString(Class<?> clazz) {
		return (clazz != null && (clazz.equals(String.class)||clazz.equals(Character.class)));
	}

	public static boolean isBoolean(Class<?> clazz) {
		return (clazz != null && (clazz.equals(boolean.class) || clazz.equals(Boolean.class)));
	}
	public static boolean isNumber(Class<?> clazz){
		return (clazz!=null && clazz.equals(Number.class));
	}
	public static boolean isList(Class<?> clazz) {
		return isHaveInterface(Collection.class, clazz);
	}

	public static boolean isMap(Class<?> clazz){
		return isHaveInterface(Map.class, clazz);
	}
	
	public static boolean isNewAble(Class<?> clazz){
		int mod=clazz.getModifiers();
		return (!Modifier.isAbstract(mod)&&!Modifier.isInterface(mod)&&Modifier.isPublic(mod));
	}
	
	public static boolean isHaveInterface(Class<?> iClass,Class<?> clazz){
		if (clazz == null)
			return false;
		if (clazz.equals(iClass))
			return true;
		if (!clazz.isInterface()) {
			if (clazz.getSuperclass() != Object.class) {
				isHaveInterface(iClass, clazz.getSuperclass());
			}
		}
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> class1 : interfaces) {
			if (isHaveInterface(iClass, class1)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isHaveAbstractClass(Class<?> aClass,Class<?> clazz){
		if(clazz==null||clazz.equals(aClass))
			return false;
		if(Modifier.isInterface(clazz.getModifiers()))
			return false;
		for (;clazz!=Object.class;clazz=clazz.getSuperclass()){
			if(clazz.equals(aClass))
				return true;
		}
		return false;
	}
	
////	public static void main(String[] args) {
////		String value="o";
////		char[] values=value.toCharArray();
////		boolean pand=true;
////		for(int i=0,j=values.length-1;i<j;i++,j--){
////			if(!(values[i]+"").equalsIgnoreCase(values[j]+"")){
////				pand=false;
////				break;
////			}
////		}
//		
//		
//		
//		Class<?> clazz=TypeValidator.class;
//		for(;clazz!=Object.class;clazz=clazz.getSuperclass()){
//			System.out.println(clazz.getName());
//		}
//		
//		System.out.println(pand?"Evet pand ...":"Hayir pand deil ");
//	}
	public static boolean isComponentModifier(int mod){
		return (!Modifier.isAbstract(mod)&&Modifier.isPublic(mod)&&!Modifier.isInterface(mod)&&!Modifier.isTransient(mod));
	}
	@SuppressWarnings("unchecked")
	public static <E> E getDefaultValue(Class<E> clazz) throws IOUtilException {

		if (isJavaPrimitive(clazz)) {
			return (E) primitiveMap.get(clazz);
		} else {
			return IOTypeUtil.createClass(clazz);
		}
	}
	public static boolean isClassOfSuperClass(Class<?> superClass,Class<?> clazz){
		return (clazz!=null&&superClass!=null&&IOUtilReflect.getSuperClasses(clazz).contains(superClass));
	}
	public static boolean isClass(Class<?> clazz){
		return (clazz!=null && (Modifier.isPublic(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers())  && !clazz.isEnum()) );
	}
	public static boolean isClassButNotAbstract(Class<?> clazz){
		return (clazz!=null && (Modifier.isPublic(clazz.getModifiers()) && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) ) );
	}
	public static boolean isAnnotation(Class<?> clazz){
		return clazz!=null&&clazz.isInterface()&&isHaveInterface(Annotation.class, clazz);	
	}
	public static boolean isHaveAnAnnotation(Class<? extends Annotation> aClass,Class<?> clazz){
		return (clazz!=null&&!Modifier.isInterface(clazz.getModifiers())&&clazz.isAnnotationPresent(aClass));
	}
	public static boolean isJavaPrimitive(Object o) {
	return (isJavaPrimitive(o.getClass()));
	}		
	public static boolean isJavaPrimitive(Class<?> clazz) {
		System.out.println(clazz.getName());
	return (clazz != null && (clazz.isPrimitive() || primitiveMap.keySet().contains(clazz)));
	}	
	
	@SuppressWarnings("unchecked")
	public static <G,O> G castGenericType(Class<G> clazz,O o){
		try{
			if(clazz==null||o==null)
				return null;
			if(clazz.equals(o.getClass())||IOTypeValidator.isHaveAbstractClass(clazz, o.getClass())||IOTypeValidator.isHaveInterface(clazz,o.getClass())){
				return (G)o;
			}else if(isJavaPrimitive(clazz)&&isJavaPrimitive(o)){
				Constructor<G> c= clazz.getDeclaredConstructor(o.getClass());
				return (G)c.newInstance(o);
			}
		}catch (Exception e) {
		}
		return null;
	}
	private static final Map<Class<?>, Object> primitiveMap = new LinkedHashMap<Class<?>, Object>();
	static {
		/**
		 * byte 0,short 0 ,int 0 ,long 0L ,float 0.0f ,double 0.0d ,char
		 * '\u0000',String (or any object) null ,boolean false
		 */
		primitiveMap.put(byte.class, 0);
		primitiveMap.put(Byte.class, 0);
		primitiveMap.put(short.class, 0);
		primitiveMap.put(Number.class, 0);
		primitiveMap.put(Short.class, 0);
		primitiveMap.put(int.class, 0);
		primitiveMap.put(Integer.class, 0);
		primitiveMap.put(long.class, 0L);
		primitiveMap.put(Long.class, 0L);
		primitiveMap.put(float.class, 0.0f);
		primitiveMap.put(Float.class, 0.0f);
		primitiveMap.put(double.class, 0.0d);
		primitiveMap.put(Double.class, 0.0d);
		primitiveMap.put(char.class, '\u0000');
		primitiveMap.put(Character.class, '\u0000');
		primitiveMap.put(String.class, "");
		primitiveMap.put(boolean.class, false);
		primitiveMap.put(Boolean.class, false);
	}

}
