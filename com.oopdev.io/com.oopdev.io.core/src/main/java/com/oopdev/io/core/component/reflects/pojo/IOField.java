package com.oopdev.io.core.component.reflects.pojo;

import java.lang.reflect.Field;

/**
 * 
 * @author kamilbukum
 *
 */
public class IOField {
	private final String fieldName;
	private final Field field;
	private IOMethodGetter getterMethod;
	private IOMethodSetter setterMethod;
	/**
	 * 
	 * @param fieldName
	 * @param field
	 */
	public IOField(String fieldName,Field field) {
		this.field=field;
		this.fieldName=fieldName;
	}
	/**
	 * 
	 * @return
	 */
	public Field getField() {
		return field;
	}
	/**
	 * 
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * 
	 * @return
	 */
	public IOMethodGetter getGetterMethod() {
		return getterMethod;
	}
	/**
	 * 
	 * @param getterMethod
	 */
	public void setGetterMethod(IOMethodGetter getterMethod) {
		this.getterMethod = getterMethod;
	}
	/**
	 * 
	 * @return
	 */
	public IOMethodSetter getSetterMethod() {
		return setterMethod;
	}
	/**
	 * 
	 * @param setterMethod
	 */
	public void setSetterMethod(IOMethodSetter setterMethod) {
		this.setterMethod = setterMethod;
	}
}
