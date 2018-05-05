package com.card.domain.switchs;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yangzhanbang
 * @date 2018/5/4 15:42
 * @desc
 */
@Data
public class CardSwitch {
	@NotNull(message = "id 不能为空")
	private Long switchId;

	@NotBlank(message = "描述不能为空")
	private String switchDesc;

	@NotNull(message = "开关状态不能为空")
	private Integer switchStatus;

	@NotNull(message = "开关状态描述不能为空")
	private String switchStatusDesc;

	@NotNull(message = "创建时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createdTime;            //创建时间

	@NotNull(message = "修改时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")

	private Date modifiedTime;        //修改时间
	@NotBlank(message = "操作人不能为空")
	private String operator="system";            //操作人
}
