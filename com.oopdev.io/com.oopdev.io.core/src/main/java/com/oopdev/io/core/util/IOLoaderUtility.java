package com.oopdev.io.core.util;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import com.oopdev.io.loader.finder.classes.loader.IOClassLoader;
import com.oopdev.io.util.validators.IOTypeValidator;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOLoaderUtility {

	/**
	 * 
	 * @param packageNames
	 * @return
	 */
	public static final List<Class<?>> getClassList(String packageNames){
		List<Class<?>> classList=IOClassLoader.getAllClasses(packageNames);
		return classList;
	}
	/**
	 * 
	 * @param packageNames
	 * @param aClass
	 * @return
	 */
	public static final List<Class<?>> getClassListByAbstract(String packageNames,Class<?> aClass){
		List<Class<?>> classList=IOClassLoader.getAllClasses(packageNames);
		List<Class<?>> abstractClassList=new LinkedList<Class<?>>();
		for (Class<?> clazz : classList) {
			boolean requiredCondition=IOTypeValidator.isClassButNotAbstract(clazz)&&!abstractClassList.contains(clazz);// Class olmalı ve listede olmamalı
			if(requiredCondition&&isHaveAnCondition(clazz, aClass, null, null)){
				abstractClassList.add(clazz);
			}
		}
		return abstractClassList;
	}
	/**
	 * 
	 * @param packageNames
	 * @param iClass
	 * @return
	 */
	public static final List<Class<?>> getClassListByInterface(String packageNames,Class<?> iClass){
		List<Class<?>> classList=IOClassLoader.getAllClasses(packageNames);
		List<Class<?>> interfaceClassList=new LinkedList<Class<?>>();
		for (Class<?> clazz : classList) {
			boolean requiredCondition=IOTypeValidator.isClassButNotAbstract(clazz)&&!interfaceClassList.contains(clazz);// Class olmalı ve listede olmamalı
			if(requiredCondition&&isHaveAnCondition(clazz, null, iClass, null)){
				interfaceClassList.add(clazz);
			}
		}
		return interfaceClassList;
	}
	/**
	 * 
	 * @param packageNames
	 * @param annClass
	 * @return
	 */
	public static final List<Class<?>> getClassListByAnnotation(String packageNames,Class<? extends Annotation> annClass){
		List<Class<?>> classList=IOClassLoader.getAllClasses(packageNames);
		List<Class<?>> annClassList=new LinkedList<Class<?>>();
		for (Class<?> clazz : classList) {
			boolean requiredCondition=IOTypeValidator.isClassButNotAbstract(clazz)&&!annClassList.contains(clazz);// Class olmalı ve listede olmamalı
			if(requiredCondition&&isHaveAnCondition(clazz, null, null, annClass)){
				annClassList.add(clazz);
			}
		}
		return annClassList;
	}
	/**
	 * 
	 * @param packageNames -- Paket altında arama islemini yapar
	 * @param abstractClass - Abstract sınıfa sahip olabilir
	 * @param interfaceClass -- interface'e sahip olabilir
	 * @param annotationClass -- Annotation'a sahip olabilir
	 * @return
	 */
	public static final List<Class<?>> getClassListByCondition(String packageNames,Class<?> abstractClass,Class<?> interfaceClass,Class<? extends Annotation> annotationClass){
		List<Class<?>> classList=IOClassLoader.getAllClasses(packageNames);
		List<Class<?>> optionClassList=new LinkedList<Class<?>>();
		for (Class<?> clazz : classList) {
			boolean requiredCondition=IOTypeValidator.isClassButNotAbstract(clazz)&&!optionClassList.contains(clazz);// Class olmalı ve listede olmamalı
				if(requiredCondition&&isHaveAnCondition(clazz, abstractClass, interfaceClass, annotationClass)){
					optionClassList.add(clazz);
				}
		}
		return optionClassList;
	}
	/**
	 * 
	 * @param clazz
	 * @param abstractClass
	 * @param interfaceClass
	 * @param annotationClass
	 * @return
	 */
	public static final boolean isHaveAnCondition(Class<?> clazz,Class<?> abstractClass,Class<?> interfaceClass,Class<? extends Annotation> annotationClass){
		boolean optionCondition=(abstractClass!=null&&IOTypeValidator.isHaveAbstractClass(abstractClass, clazz));// Abstract sınıfa sahip olabilir
		optionCondition=optionCondition||(interfaceClass!=null&&IOTypeValidator.isHaveInterface(interfaceClass, clazz));// Interface sınıfa sahip olabilir
		optionCondition=optionCondition||(annotationClass!=null&&IOTypeValidator.isHaveAnAnnotation(annotationClass, clazz));//Annotation'a sahip olabilir 
		return optionCondition;
	}
}
