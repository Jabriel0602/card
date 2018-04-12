package com.card.domain.config;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * Created with card
 * @author yangzhanbang
 * @date 2018/2/8 17:28
 */
@Data
public class Config {

	@NotBlank(message = "orderId不能为空")
	private Long configId;		//订单id
	@NotNull(message = "configType不能为空")
	private Integer configType;	//配置类别
	@NotBlank(message = "key不能为空")
	private String key;			//配置键值
	@NotBlank(message = "value不能为空")
	private String value;			//配置valule
	@NotBlank(message = "operate不能为空")
	private String operator;		//操作人
	@NotNull(message = "修改时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date modifiedTime;	//修改时间
	@NotNull(message = "创建时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createdTime;		//创建时间
	@NotNull(message = "数据是否有效不能为空")
	private Byte yn;				//数据是否有效 0无效 1有效
}
