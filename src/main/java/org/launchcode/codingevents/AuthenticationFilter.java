package org.launchcode.codingevents;

import org.launchcode.codingevents.controllers.UserController;
import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements HandlerInterceptor {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserController userController;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		// from the http request coming in (like GET events), get the current session
		HttpSession session = request.getSession();

		// from the current session, get the user
		User user = userController.getUserFromSession(session);

		// if the user is present/logged in, return true
		if (user != null) {
			return true;
		}

		// otherwise, redirect and then return false
		response.sendRedirect("/login");
		return false;
	}
}
