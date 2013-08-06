package com.magiccube.v2.controller;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magiccube.v2.service.SuggestService;

@Controller
@RequestMapping(value = "/suggest")
public class SuggestController {
	
	@Autowired
	private SuggestService suggestService;
	
	@RequestMapping(value = "")
	public String list(Model model, ServletRequest request) {
		model.addAttribute("suggests",suggestService.getAllSuggest());
		return "suggest/view";
	}

}
