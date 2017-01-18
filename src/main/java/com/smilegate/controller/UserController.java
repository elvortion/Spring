package com.smilegate.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilegate.domain.UserData;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public ArrayList<UserData> list = new ArrayList<UserData>();
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/create")
	public String join(String userId, String password, String name, String email) {
		list.add(new UserData(userId, password, name, email));
		log.debug("현재 가입된 유저 수 : " + list.size());
		//System.out.println("현재 가입된 유저 수 : " + list.size());
		
		return "redirect:/user/list";
	}
	
	public String join(UserData newUserData) { // 자동으로 Mapping
		list.add(newUserData);
		System.out.println("현재 가입된 유저 수 : " + list.size());
		
		return "user/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", list);
	
		return "user/list";
	}
	
}
