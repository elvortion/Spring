package com.smilegate.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.smilegate.domain.Question;

@Controller
//@RequestMapping("/qna")
public class QAController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	public ArrayList<Question> list = new ArrayList<Question>();
	
	@PostMapping("/qna/questions")
	public String ask(Question question) {
		list.add(question);
		log.debug("현재 질문의 개수 : " + list.size());
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String view(Model model) {
		model.addAttribute("list", list);
		return "/qna/index";
	}
}
