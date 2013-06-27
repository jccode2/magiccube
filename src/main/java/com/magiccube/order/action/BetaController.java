package com.magiccube.order.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/beta")
public class BetaController {

	@RequestMapping("")
	public String main(Model model) {
		return "forward:/order";
	}
}
