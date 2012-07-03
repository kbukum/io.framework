package com.oopdev.io.json.service.annotations;

/**
 * 
 * @author kamilbukum
 *
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface IOJsonMethod {
	public String value();
}
