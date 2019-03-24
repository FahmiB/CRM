package com.fahmi.crm2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value="/home")
	public String home(Model theModel) {
		return "home";
	}
}
