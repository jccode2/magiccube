package com.magiccube.bulletin.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magiccube.bulletin.model.BulletinVO;


@Controller
@RequestMapping("/shop/bulletin")
public class BulletinController {

	final static Logger logger = LoggerFactory.getLogger(BulletinController.class);
	
	@RequestMapping("")
	public String toBulletinList() {
		
		return "shop/bulletin";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public boolean saveBulletin(@ModelAttribute BulletinVO bulletinVO) {
		
		return false;
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public boolean deleteBulletin(@PathVariable int id) {
		
		return false;
	}
}
