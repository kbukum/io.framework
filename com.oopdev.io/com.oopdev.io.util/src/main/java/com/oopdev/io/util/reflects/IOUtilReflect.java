package com.oopdev.io.util.reflects;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.oopdev.io.util.app.IOUtilConstants;
import com.oopdev.io.util.exceptions.IOUtilException;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOUtilReflect {

	public static List<Field> getFieldList(Class<?> clazz) {
		if (clazz == null)
			return null;
		List<Class<?>> allClasses = getSuperClasses(clazz);
		allClasses.add(clazz);
		List<Field> fields = new LinkedList<Field>();
		for (Class<?> lClass : allClasses) {
			for (Field field : lClass.getDeclaredFields()) {
				fields.add(field);
			}
		}
		return fields;
	}


	public static List<Class<?>> getSuperClasses(Class<?> clazz) {
		List<Class<?>> superClassList = new LinkedList<Class<?>>();
		if (clazz != null) {
			for (; clazz.getSuperclass() != Object.class; clazz = clazz
					.getSuperclass()) {
				superClassList.add(clazz.getSuperclass());
			}
		}
		return superClassList;
	}

	public static List<Method> getMethodByAnnotation(
			Class<? extends Annotation> aClass, Class<?> clazz) {
		List<Method> methods = new LinkedList<Method>();
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.isAnnotationPresent(aClass)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public static String getMethodUniqueName(Method method) {
		if (method == null)
			return null;
		String methodName = method.getName();
		if (method.getParameterTypes() != null
				&& method.getParameterTypes().length > 0) {
			methodName = methodName + IOUtilConstants.METHOD_UNIQUE_PARSE;
			for (Class<?> type : method.getParameterTypes()) {
				methodName += type.getName();
			}
		}

		return methodName;
	}

	public static String getMethodNameParseUniqueName(String methodUniqueName) {
		return (methodUniqueName == null ? "" : methodUniqueName
				.split(IOUtilConstants.METHOD_UNIQUE_PARSE)[0]);
	}

	@SuppressWarnings("unchecked")
	public static <E extends Annotation> E getAnnotationOfMethodParameter(Class<? extends Annotation> aClass, Method method, int index) throws IOUtilException {
		if (method.getParameterTypes().length <= index)
			throw new IOUtilException("IO method parameter index is bound error ! ");

		if (method.getParameterAnnotations()[index] == null)
			throw new IOUtilException("IO Method input name must be named annotation declared ! ");
		if (method.getParameterAnnotations()[index].length == 0)throw new IOUtilException("IO Method input name must be named annotation declared ! ");

		for (Annotation annotation : method.getParameterAnnotations()[index]) {
			if (annotation.annotationType().equals(aClass))
				return (E) annotation;
		}
		throw new IOUtilException(
				"IO Method input name must be named annotation declared ! ");
	}

}