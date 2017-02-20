package com.smilegate.controller;

import javax.servlet.http.HttpSession;

//import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smilegate.domain.Question;
import com.smilegate.domain.QuestionRepository;
import com.smilegate.domain.User;
import com.smilegate.utils.HttpSessionUtils;

@Controller
@RequestMapping("/qna")
public class QAController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
//	public ArrayList<Question> list = new ArrayList<Question>();
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String viewQuestionForm(HttpSession session) {
		log.debug("qna/form [GET]");
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		log.debug(loginUser.toString());
		
		//model.addAttribute("user", questionRepository.findOne(id));
		
		return "/qna/form";
	}
	
	@PostMapping("/questions")
	public String ask(Question newQuestion, HttpSession session) {
		User currentUser = HttpSessionUtils.getUserFromSession(session);
		newQuestion.setWriter(currentUser);
		log.debug(newQuestion.toString());
		log.debug("currentUser : " + currentUser);
		questionRepository.save(newQuestion);

		return "redirect:/qna/list";
	}
	
	@GetMapping("/list")
	public String viewQuestionList(Model model) {
		model.addAttribute("list", questionRepository.findAll());
	
		return "/qna/index";
	}
	
	@GetMapping("/{id}/show")
	public String viewQuestionDetail(@PathVariable long id, Model model) {
		Question question = questionRepository.findOne(id);
		model.addAttribute("Question", question);
		
		return "/qna/show";
	}
	
	@GetMapping("/{id}/edit")
	public String viewUpdateForm(@PathVariable long id, Model model, HttpSession session) {
		Question question = questionRepository.findOne(id);
		User user = HttpSessionUtils.getUserFromSession(session);
		if (!question.isMatchWriter(user)) {
			log.debug("작성자 이외에는 질문을 수정 할 수 없습니다.");
			return "redirect:/qna/" + Long.toString(id) + "/show"; 
		}
		model.addAttribute("Question", question);
		return "/qna/updateForm";
	}
	
//	@RequestMapping(method = RequestMethod.PUT)
	@PutMapping("/{id}/edit")
//	@PostMapping("/{id}/edit")
	public String updateQuestion(@PathVariable long id, Question newQuestion) {
		Question question = questionRepository.findOne(id);
		question.update(newQuestion);
		questionRepository.save(question);
		log.debug("return URL : " + "/qna/" + Long.toString(id) + "/show");
//		return "/qna/" + Long.toString(id) + "/show";
		return "/qna/index";
	}
	
	@DeleteMapping("/{id}")
	public String removeQuestion(@PathVariable long id, HttpSession session) {
		User user = HttpSessionUtils.getUserFromSession(session);
		if (user.getId() != id) {	// 로그인한 유저가 생성한 질문만 삭제 가능
			log.debug("작성자 이외에는 질문을 삭제 할 수 없습니다.");
			return "redirect:/qna/list";
		}
		Question question = questionRepository.findOne(id);
		questionRepository.delete(question);
		
		return "redirect:/qna/list";
	}
}
