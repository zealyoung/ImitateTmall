package com.zeal.tmall.annotation;

import com.zeal.tmall.pojo.enums.RoleAuthority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface HasAuthority {
    RoleAuthority value() default RoleAuthority.NORMAL;
}
