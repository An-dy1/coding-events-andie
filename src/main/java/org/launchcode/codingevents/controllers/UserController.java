package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private static final String userSessionKey = "user";

	// todo: why public method when we've been writing private?
	public User getUserFromSession(HttpSession session) {

		// using a passed in HttpSession object, we will get the "user" attribute from it
		// .getAttribute returns an object, so we have to cast to an integer
		Integer userId = (Integer) session.getAttribute(userSessionKey);

		// no user id on the session means they are not logged in, return no user
		if (userId == null) {
			return null;
		}

		// if there is a user id on the session, retrieve the optional User via User Repository
		Optional<User> user = userRepository.findById(userId);

		// if there is no associated user, return null
		if (user.isEmpty()) {
			return null;
		}

		// otherwise, return the user object
		return user.get();
	}

	// set the user's id on the session's "user" attribute
	private static void setUserInSession(HttpSession session, User user) {
		session.setAttribute(userSessionKey, user.getId());
	}
}

