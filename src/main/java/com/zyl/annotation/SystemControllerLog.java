package com.zyl.annotation;

import java.lang.annotation.*;
//自定义注解
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行 (运行时)
@Documented //生成文档
public @interface SystemControllerLog {
    String descrption() default "";
    String user() default "";
}
