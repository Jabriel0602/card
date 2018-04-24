package com.card.domain.order;

import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/9
 * Description：
 */
@Data
public class Order {

	@NotNull(message = "订单id不能为空")
	private Long orderId;        // 订单id

	@NotBlank(message = "商品id不能为空")
	private String skuId;        //商品id
	@NotBlank(message = "商品名称不能为空")
	private String skuName;    //商品名称

	@NotNull(message = "商品名称不能为空")
	private Long skuMoney;     //商品金额

	@NotBlank(message = "卡片id不能为空")
	private String cardId;        //卡片id
	@NotBlank(message = "卡片类型不能为空")
	private String cardType;    //卡片类型

	@NotNull(message = "用户不能为空")
	private Long userId;    //用户
	//	@NotBlank(message = "用户手机号不能为空")
	private String phone;        //用户手机号

	@NotNull(message = "提单时间不能为空")
	private Date submitTime;    //提单时间


	//	@NotBlank(message = "支付号不能为空")
	private String payNo;        //支付号
	private Long money;
	@NotNull(message = "支付时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date payTime;        //支付时间

	//相关状态信息
	@NotNull(message = "支付状态不能为空")
	private Integer payStatus;        //支付状态
	@NotNull(message = "充值状态不能为空")
	private Integer rechargeStatus;    //充值状态

	@NotNull(message = "退款状态不能为空")
	private Integer refundStatus;    //退款状态
	@NotNull(message = "订单状态不能为空")
	private Integer orderStatus;    //订单状态
	@NotNull(message = "最终状态不能为空")
	private Integer finaStatus;        //最终状态

	@NotNull(message = "创建时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createdTime;        //创建时间
	@NotNull(message = "修改时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date modifyTime;        //修改时间
	@NotNull(message = "是否有效不能为空")
	private Byte yn;                //是否有效


	private Integer preStatus;//订单前置状态 非表字段
	private Integer pageIndex;//当前页  非表字段
	private Integer startRow;//开始行  非表字段
	private Integer pageSize;//每页显示多少条  非表字段
	private String previousCreated;//创建日期起始  非表字段
	private String postCreated;//创建日期结束  非表字段
	private String previousPayTime;//支付时间起始  非表字段
	private String postPayTime;//支付时间结束  非表字段
	private String previousSubmitTime;//提单时间起始  非表字段
	private String postSubmitTime;//提单时间结束  非表字段

}
