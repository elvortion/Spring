package com.smilegate.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilegate.domain.User;
import com.smilegate.domain.UserRepository;
import com.smilegate.utils.HttpSessionUtils;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/form/create")
	public String viewJoinForm() {
		log.debug("/users/form/create [GET]");
		return "/users/create";
	}

	@PostMapping("/create")
	public String userJoin(User newUser) {
		log.debug("/users/form/create [POST]");
		
		//TODO 입력 값에 대한 어떠한 확인 로직이 없음
		userRepository.save(newUser);
		
		return "redirect:/";
	}

	@GetMapping("/list")
	public String index(Model model) {
		log.debug("/users/list [GET]");
		
		model.addAttribute("userList", userRepository.findAll());
		
		return "users/index";
	}

	@GetMapping("/login") // 로그인 login.html 가져오기
	public String viewUserLoginPage() {
		log.debug("/users/login [GET]");
		
		//TODO login error에 대한 어떠한 로직도 없음
		return "/users/login";
	}

	@GetMapping("/profile")
	public String show(String userId, Model model) {
		log.debug("/users/profile [GET]");
		User currentUser = userRepository.findByUserId(userId);
		model.addAttribute("UserData", currentUser);
		
		return "/users/profile";
	}

	@PostMapping("/enter") // 로그인 진행
	public String login(String userId, String password, HttpSession session) {
		log.debug("/users/checklogin [POST]");
		User currentUser = userRepository.findByUserId(userId);
		if (currentUser == null) { // 예외처리, 유저가 존재하지 않는 경우
			log.debug("유저가 존재하지 않습니다!");
			return "redirect:/users/login.html";
		}

		if (!currentUser.isMatchPassword(password)) {
			log.debug("로그인 실패. 비밀번호를 확인해주세요.");
			return "redirect:/users/login.html";
		} else {
			log.debug("로그인 성공!");
			// 한번 세션을 담아놓으면 HTML 어디에서나 그대로 사용 할 수 있음
			session.setAttribute("loginUser", currentUser); // Key Value

			log.debug("로그인 유저 = " + currentUser.getUserData().toString());
			return "redirect:/qna/list";
		}
	}

	@GetMapping("/logout") // 로그아웃 진행
	public String logout(HttpSession session) {
		log.debug("/users/logout [GET]");
		session.removeAttribute("loginUser");
		return "/";
	}

	@GetMapping("{id}/form") // 개인정보 수정 form.html 가져오기
	public String viewUserUpdateForm(@PathVariable long id, Model model, HttpSession session) {
		log.debug("/users/{id}/form [GET] 유저 생성 폼 로드");
		checkOwner(id, session);

		model.addAttribute("user", userRepository.findOne(id));
		return "/users/updateForm";
	}

	private void checkOwner(long id, HttpSession session) { // 인증
		log.debug("This is check login user module");
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인하지 않은 사용자입니다.");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!loginUser.isMatchId(id)) {
			throw new IllegalStateException("허용되지 않은 유저입니다.");
		}
	}

	// @PostMapping("{id}/update")
	@PutMapping("{id}/update") // 개인정보 수정 진행
	public String userUpdate(@PathVariable long id, User user, HttpSession session) {
		log.debug("/users/{id}/update [PUT]");

		checkOwner(id, session);

		log.debug("변경 전 = " + user.toString());
		User dbUser = userRepository.findOne(id);
		dbUser.update(user);
		log.debug("변경 후 = " + dbUser.toString());
		userRepository.save(dbUser);
		return "redirect:/users/list";
	}

}
