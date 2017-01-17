package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(String name, int age, Model model) {
		// Model Class을 통해 URL에서 받아온 데이터들을 HTML 안에서 사용할 수 있는 TEXT로 만들어줌
		System.out.println("name : " + name + " | age : " + Integer.toString(age));
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "hello";	// hello.html link
	}
	
	
}
