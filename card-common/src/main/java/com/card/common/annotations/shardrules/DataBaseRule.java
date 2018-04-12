package com.card.common.annotations.shardrules;

import java.lang.annotation.*;

/**
 * @author yangzhanbang
 * @date 2018/3/26 17:48
 * @desc
 */

@Documented     // 表明 注解会被包含在Java API文档中。
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataBaseRule {

	String name() default "";

}
