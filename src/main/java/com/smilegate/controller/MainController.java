package com.smilegate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/")
	public String main() {
		log.debug("/ [GET]");
		return "users/index";
	}
}
