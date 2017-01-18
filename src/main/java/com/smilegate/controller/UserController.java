package com.smilegate.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilegate.domain.UserData;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public ArrayList<UserData> list = new ArrayList<UserData>();
	
	@PostMapping("/create")
	public String join(String userId, String password, String name, String email) {
		// public String join(UserData newUserData)도 동일. 자동으로 Mapping.
		
		list.add(new UserData(userId, password, name, email));
		System.out.println("현재 가입된 유저 수 : " + list.size());
		
		//model.addAttribute("name", name);
		//model.addAttribute("age", age);
		
		return "";
	}
	
	public String join(UserData newUserData) {
		list.add(newUserData);
		System.out.println("현재 가입된 유저 수 : " + list.size());
		
		return "";
	}
}
