package com.oopdev.io.json.service.annotations;

/**
 * 
 * @author kamilbukum
 *
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface IOJsonService {
    boolean secure() default false;
    boolean autoLogin() default false;
    boolean crossDomain() default false;
}
