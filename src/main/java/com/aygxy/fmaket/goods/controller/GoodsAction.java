package com.aygxy.fmaket.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("goods")
public class GoodsAction {
	
	@RequestMapping("test.action")
	@ResponseBody
	public String test(Model model){
		return "SUCCESS TOKEN";
	}
}
