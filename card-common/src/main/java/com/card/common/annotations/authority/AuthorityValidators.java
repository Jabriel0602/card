package com.card.common.annotations.authority;

import java.lang.annotation.*;

/**
 * @author yangzhanbang
 * @date 2018/3/26 17:59
 * @desc
 */
@Documented     // 表明 注解会被包含在Java API文档中。
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface AuthorityValidators {
	String name() default "";

	AuthorityValidator[] value() default {};

}
