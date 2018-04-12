package com.card.domain.refund;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */

@Data
public class Refund {

	@NotNull(message = "订单id不能为空")
	private Long orderId;            //订单id
	@NotNull(message = "退款id不能为空")
	private Long refundId;        //退款id

	@NotNull(message = "退款类型不能为空")
	private Byte refundType;        //退款类型

	@NotNull(message = "退款金额不能为空")
	private Long refundFee;        //退款金额
	@NotNull(message = "退款状态不能为空")
	private Byte refundStatus;    //退款状态

	@NotNull(message = "创建时间不能为空")
	private Date createdTime;        //创建时间
	@NotBlank(message = "修改时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date modifiedTime;    //修改时间
	@NotNull(message = "完成时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date finishTime;        //完成时间

	@NotNull(message = "是否有效不能为空")
	private Byte yn;                //是否有效 1 有效 0无效
}
