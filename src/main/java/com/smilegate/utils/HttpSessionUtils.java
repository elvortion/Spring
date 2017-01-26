package com.smilegate.utils;

import javax.servlet.http.HttpSession;

import com.smilegate.domain.UserData;

public class HttpSessionUtils {	
	// 로그인 한 유저가 없는 경우
	public static boolean isLoginUser(HttpSession session) {
		return session.getAttribute("loginUser") != null;
	}
	
	// 로그인 한 유저가 있는 경우, 유저를 반환
	public static UserData getUserFromSession(HttpSession session) {
		if (!isLoginUser(session)) {
			return null;
		}
		return (UserData)session.getAttribute("loginUser");
	}
}
