package com.card.domain.task;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * Created with card
 * @author: yangzhanbang
 * Date: 2018/2/8
 * Time: 17:28
 */
@Data
public class Task {

	@NotNull(message = "任务id不能为空")
	private Long taskId;		//任务id
	@NotNull(message = "任务类型不能为空")
	private Integer taskType;	//任务类型
	@NotNull(message = "任务描述不能为空")
	private String taskDesc;	//任务描述


//	@NotNull(message = "业务类型不能为空")
//	private Integer orderType;		//任务对应的订单 类型
//	@NotBlank(message = "类型描述不能为空")
//	private String orderDesc;	//任务对应的订单 类型描述
	@NotNull(message = "业务id不能为空")
	private Long orderId;		//任务对应的订单 编号



	@NotNull(message = "重试策略不能为空")
	private Integer retryStrategy;	//重试策略
	@NotNull(message = "重试次数不能为空")
	private Integer retryTimes;		//重试次数
	@NotNull(message = "最大重试次数不能为空")
	private Integer maxRetryTimes;	//最大重试次数

	@NotNull(message = "执行状态不能为空")
	private Integer executeStatus;		//执行状态

	@NotNull(message = "下次重试时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date nextExecuteTime;	//下次重试时间

//	@NotNull(message = "任务截止时间不能为空")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//	private Date finalTime;			//任务截止时间

	@NotNull(message = "创建时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createdTime;			//创建时间
	@NotNull(message = "修改时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date modifiedTime;		//修改时间

	private String operator="System";			//操作人

	@NotNull(message = "数据是否有效不能为空")
	private Byte yn;					//数据是否有效 0无效 1有效

}
