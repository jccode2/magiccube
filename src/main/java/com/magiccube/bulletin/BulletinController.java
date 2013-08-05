package com.magiccube.bulletin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/shop/bulletin")
public class BulletinController {

	final static Logger logger = LoggerFactory.getLogger(BulletinController.class);
	
	@RequestMapping("")
	public String toBulletinList() {
		
		return "shop/bulletin";
	}
}
