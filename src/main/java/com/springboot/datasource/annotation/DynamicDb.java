package com.springboot.datasource.annotation;

import com.springboot.datasource.enums.DbEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicDb {
    String value() default DbEnum.MASTER;
}
