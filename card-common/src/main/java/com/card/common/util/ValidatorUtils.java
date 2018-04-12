package com.card.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/8 16:16
 */
@Slf4j
public class ValidatorUtils {
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> Map<String, String> validate(T obj) {
		Map<String, StringBuilder> errorMap = new HashMap<>();
		Set<ConstraintViolation<T>> set = validator.validate(obj);
		if (set != null && set.size() > 0) {
			String property = null;
			for (ConstraintViolation<T> cv : set) {
				//这里循环获取错误信息，可以自定义格式
				property = cv.getPropertyPath().toString();
				if (errorMap.get(property) != null) {
					errorMap.get(property).append("," + cv.getMessage());
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(cv.getMessage());
					errorMap.put(property, sb);
				}
			}
		}
		return errorMap.entrySet().stream().collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().toString()));
	}
}
