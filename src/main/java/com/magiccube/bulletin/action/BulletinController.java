package com.magiccube.bulletin.action;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magiccube.bulletin.model.BulletinVO;
import com.magiccube.bulletin.service.BulletinService;


@Controller
@RequestMapping("/shop/bulletin")
public class BulletinController {

	final static Logger logger = LoggerFactory.getLogger(BulletinController.class);
	
	@Autowired
	private BulletinService bulletinService;
	
	@RequestMapping("")
	public String toBulletinList(Model model) {
		List<BulletinVO> list = bulletinService.findAllBulletins();
		model.addAttribute("bulletinList", list);
		return "shop/bulletin";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody boolean saveBulletin(@Valid BulletinVO bulletinVO) {
		System.out.println(bulletinVO);
		return false;
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public @ResponseBody boolean deleteBulletin(@PathVariable int id) {
		
		return false;
	}

	public BulletinService getBulletinService() {
		return bulletinService;
	}

	public void setBulletinService(BulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}
}
