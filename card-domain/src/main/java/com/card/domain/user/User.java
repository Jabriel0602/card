package com.card.domain.user;

import com.card.common.util.Base64Util;
import com.google.common.base.Charsets;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Data
public class User {

	@NotNull(message = "账号不能为空")
	private Long userId;        //主键id

	@NotBlank(message = "用户类型不能为空")
	private String userType;    //用户类型

	@NotNull(message = "模块权限不能为空")
	private Integer moduleTypeLimits=0;
	@NotNull(message = "方法权限不能为空")
	private Integer methodTypeLimits=0;

	@NotBlank(message = "用户名不能为空")
	private String userName;    //用户名
	@NotBlank(message = "密码不能为空")
	private String password;    //密码

	private String sex;
	private String phone;
	private String birthday;

	@NotNull(message = "创建时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;    //创建时间
	@NotNull(message = "修改时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date modifyTime;    //修改时间
	@NotBlank(message = "操作人不能为空")
	private String operator="system";    //操作人
	@NotNull(message = "是否有效不能为空")
	private Byte yn=1;            //是否有效 0无效 1有效



}
