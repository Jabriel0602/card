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

	public synchronized long getId(SequenceEnum sequenceEnum) {
		return sequenceUtil.get(sequenceEnum.getName());
	}

	/**
	 * id常量
	 */

	public enum SequenceEnum {
		CARD(1, "CARD_ID"),
		TASK(2, "TASK_ID"),
		ORDER(3, "ORDER_ID"),
		ORDER_DETAIL(4, "ORDER_DETAIL_ID"),
		SMS(5, "SMS_ID"),
		REFUND(6, "REFUND_ID"),
		CONFIG(7, "CONFIG_ID"),
		USER(8, "USER_ID"),
		ADIMAGE(9, "ADIMAGE_ID");


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