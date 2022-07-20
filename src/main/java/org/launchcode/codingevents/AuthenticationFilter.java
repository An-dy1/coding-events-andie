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
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserController userController;

	private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		if (isWhitelisted(request.getRequestURI())) {
			return true;
		}

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

	private static boolean isWhitelisted(String path) {
		for (String pathRoot : whitelist) {
			if (path.startsWith(pathRoot)) {
				return true;
			}
		}
		return false;
	}
}
