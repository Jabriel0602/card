package com.card.common.annotations.authority;

import java.lang.annotation.*;

/**
 * @author yangzhanbang
 * @date 2018/3/26 17:59
 * @desc
 */
// 表明 注解会被包含在Java API文档中。
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
/**
 * 权限校验器集合
 * 复用 AuthorityValidators 中有多个 AuthorityValidator
 */
@Repeatable(value = AuthorityValidators.class)
public @interface AuthorityValidator {

	String name() default "";

	OperateTypeEnum operateEnum() default OperateTypeEnum.ALL;

}
