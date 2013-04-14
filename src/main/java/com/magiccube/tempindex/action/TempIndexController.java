package com.magiccube.tempindex.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;


/**
 * 临时首页控制器
 
function			method				url
---------			------				--------
每日菜单设置			GET					/tempindex/setmenu

 * 
 * @author xchen
 *
 */
@Controller
@RequestMapping("/tempindex")
public class TempIndexController {
	
	public static Map<String,String> info = new HashMap<String,String>(15);
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/setmenu")
	@ResponseBody
	public String setMenu(WebRequest request) {
		info.put("price1", (String) request.getParameter("price1"));
		info.put("price2", (String) request.getParameter("price2"));
		info.put("price3", (String) request.getParameter("price3"));
		info.put("price4", (String) request.getParameter("price4"));
		info.put("price5", (String) request.getParameter("price5"));
		info.put("mainfood1", (String) request.getParameter("mainfood1"));
		info.put("mainfood2", (String) request.getParameter("mainfood2"));
		info.put("mainfood3", (String) request.getParameter("mainfood3"));
		info.put("mainfood4", (String) request.getParameter("mainfood4"));
		info.put("mainfood5", (String) request.getParameter("mainfood5"));
		info.put("secondfood1", (String) request.getParameter("secondfood1"));
		info.put("secondfood2", (String) request.getParameter("secondfood2"));
		info.put("secondfood3", (String) request.getParameter("secondfood3"));
		info.put("secondfood4", (String) request.getParameter("secondfood4"));
		info.put("secondfood5", (String) request.getParameter("secondfood5"));
		return "update success";
	}
	
}
