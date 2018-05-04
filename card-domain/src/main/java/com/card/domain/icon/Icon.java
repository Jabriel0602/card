package com.card.domain.icon;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Data
public class Icon {
	/**
	 * 日期只需要判断非空非null，因为如果日期格式错误，无法转换成data
	 */
	@NotNull(message = "主键adImageId不能为空")
	private Long id;                        //主键id
	@NotBlank(message = "轮播图描述不能为空")
	private String desc;                    //广告图描述
	@NotBlank(message = "轮播图url不能为空")
	private String imgUrl;                 //广告图片URL
	@NotBlank(message = "轮播图跳转url不能为空")
	private String responseUrl;           //跳转链接,广告商品URL
	@NotNull(message = "创建时间必须是现在或过去的时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createdTime;           //创建时间
	@NotNull(message = "修改时间必须是现在或过去的时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date modifiedTime;          //修改时间
	@NotBlank(message = "operator不能为空")
	private String operator;               //操作人
	@Min(value = -500, message = "权重不能低于-500")
	@Max(value = 500, message = "权重不能高于500")
	private Integer weight;                //权重
	@NotNull(message = "putOn不能为空")
	private Boolean putOn=true;                 //是否启用
	private Boolean releaseStatus=false;        //是否发布
	@NotNull(message = "startTime不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;              //开始时间
	@NotNull(message = "endTime不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;                //结束时间

}
