package com.cq.kt.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tool:IntelliJ IDEA
 * Description:
 *
 * @ Author:两袖青蛇
 */
@Target({ElementType.METHOD})//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时生效
public @interface MethodExporter
{

}
