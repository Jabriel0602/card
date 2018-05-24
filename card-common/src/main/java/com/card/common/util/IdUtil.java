package com.card.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Component
public class IdUtil {

	@Autowired
	private SequenceUtil sequenceUtil;

	public void setSequenceUtil(SequenceUtil sequenceUtil) {
		this.sequenceUtil = sequenceUtil;
	}

	/**
	 * 获取 该表的下一个id值
	 */
	public synchronized long getId(SequenceEnum sequenceEnum) {
		return sequenceUtil.get(sequenceEnum.getName());
	}

	/**
	 * id常量 枚举
	 */
	public enum SequenceEnum {
		CARD(1, "CARD_ID"),
		ORDER(2, "ORDER_ID"),
		REFUND(3, "REFUND_ID"),
		SWITCH(4, "SWITCH_ID"),
		USER(5, "USER_ID"),
		ICON(6, "ICON_ID"),
		ADIMAGE(7, "ADIMAGE_ID"),
		TASK(8, "TASK_ID");

		private Integer code;
		private String name;
		SequenceEnum(Integer code, java.lang.String name) {
			this.code = code;
			this.name = name;
		}
		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "SequenceConstants{" +
					"code=" + code +
					", name='" + name + '\'' +
					'}';
		}
	}
}