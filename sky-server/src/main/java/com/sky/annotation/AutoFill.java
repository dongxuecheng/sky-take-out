package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的注解，用于标识某些方法功能字段需要自动填充
 */

@Target(ElementType.METHOD)//表示该注解的目标是方法
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    //这里的operation是该项目自定义的枚举类
    //对应着数据库的操作类型，update，insert
    OperationType value();
}
