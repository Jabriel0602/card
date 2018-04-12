package com.card.domain.adimage;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Data
public class AdImage {
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
	@Size(min = -50, max = 50,message = "weight范围区间[-50,50]")
	private Integer weight;                //权重
	@NotNull(message = "putOn不能为空")
	private Boolean putOn;                 //是否启用
	@NotNull(message = "releaseStatus不能为空")
	private Boolean releaseStatus;        //是否发布
	@NotNull(message = "startTime不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startTime;              //开始时间
	@NotNull(message = "endTime不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endTime;                //结束时间

}
