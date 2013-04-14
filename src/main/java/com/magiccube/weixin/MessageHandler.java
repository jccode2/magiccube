package com.magiccube.weixin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/weixin")
public class MessageHandler {
	
	@RequestMapping("/test")
	@ResponseBody
	public String setMenu(WebRequest request) {
		System.out.println("received message:"+request);
		return null;
	}
}
