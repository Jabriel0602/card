package com.card.domain.commodity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Data
public class Commodity {

	/**
	 * 商家ID
	 */
	private Integer venderId;

	/**
	 * 商家名称
	 */
	private String venderName;

	/**
	 * 商品ID
	 */
	private Long skuId;

	/**
	 * 商品名称
	 */
	private String skuName;
}
