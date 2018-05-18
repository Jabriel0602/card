package com.card.controller;

import com.card.domain.MethodTypeEnum;
import com.card.domain.result.APIResult;
import com.card.domain.switchs.CardSwitch;
import com.card.domain.switchs.SwitchEnum;
import com.card.service.switchs.SwitchService;
import com.card.service.user.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @date 2018/5/4 15:35
 * @desc
 */
@Controller
@RequestMapping("/switchs")
public class SwitchController {

	@Autowired
	private UserService userService;
	@Autowired
	private SwitchService switchService;

	@RequestMapping("")
	public String listSwitch(Map map) {
		List<CardSwitch> cardSwitchList = Lists.newArrayList();
		for (SwitchEnum switchEnum:SwitchEnum.values()) {
			cardSwitchList.add(switchService.select(switchEnum.getCode()));
		}
		Boolean switchFlag = userService.getMethodTypeLimitByCurrentUser(MethodTypeEnum.SWITCH);
		map.put("switchAuthorityFlag",switchFlag);
		map.put("switch",new CardSwitch());
		map.put("SwitchEnums",SwitchEnum.values());
		map.put("SwitchEnum",SwitchEnum.class);
		map.put("switchList",cardSwitchList);

		return "switch/list";
	}

	@PutMapping("/{switchId}")
	@ResponseBody
	public APIResult<Integer> cartSwitch(@PathVariable Long switchId) {
		return new APIResult<Integer>(switchService.updateSwitchStatus(switchId));
	}
}