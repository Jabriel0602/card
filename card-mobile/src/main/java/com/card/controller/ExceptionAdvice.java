package com.card.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public String handException(HttpServletRequest request, Exception e) throws Exception {
		e.printStackTrace();
		return e.getMessage();
	}
}
