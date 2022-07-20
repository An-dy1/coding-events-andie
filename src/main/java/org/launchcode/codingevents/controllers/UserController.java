package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.launchcode.codingevents.models.dto.LoginFormDTO;
import org.launchcode.codingevents.models.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

	@GetMapping("/register")
	public String displayRegistrationForm(Model model) {
		model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
		model.addAttribute("title", "Register");
		return "users/register";
	}

	@PostMapping("/register")
	public String processRegistrationForm(
			@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO,
			Errors errors,
			Model model,
			HttpServletRequest request) {

		// if form errors, return to the form with the errors
		if (errors.hasErrors()) {
			model.addAttribute("title", "Register");
			return "users/register";
		}

		// otherwise, find the user in the repository
		User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());

		// if there's already a user there, don't register them, return 'user already exists error' and the registration form
		if (existingUser != null) {
			errors.rejectValue("username", "username.alreadyexists", "a user with that username already exists");
			model.addAttribute("title", "Register");
			return "users/register";
		}

		// otherwise, get the password and verifyPassword from the DTO
		String password = registrationFormDTO.getPassword();
		String verifyPassword = registrationFormDTO.getVerifyPassword();

		// if they aren't equal, don't register them, return 'passwords don't match' and the registration form
		if (!password.equals(verifyPassword)) {
			errors.rejectValue("password", "passwords.mismatch", "passwords do not match");
			model.addAttribute("title", "Register");
			return "users/register";
		}

		// if the passwords are equal, then create a new User object using the value of username and (hashed) password
		User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
		// save user in the database
		userRepository.save(newUser);
		// and set the "user" attribute in the current HTTP session with the new user
		setUserInSession(request.getSession(), newUser);

		return "redirect:";
	}

	@GetMapping("/login")
	public String displayLoginForm(Model model) {

		model.addAttribute(new LoginFormDTO());
		model.addAttribute("title", "Login");
		return "users/login";
	}

	@PostMapping("/login")
	public String processLoginRequest(@Valid @ModelAttribute LoginFormDTO loginFormDTO, Errors errors, Model model, HttpServletRequest request) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Login");
			return "users/login";
		}

		User user = userRepository.findByUsername(loginFormDTO.getUsername());

		if (user == null) {
			errors.rejectValue("username", "username.doesnotexist", "there is no user with that information");
			model.addAttribute("title", "Login");
			return "users/login";
		}

		if (!user.isMatchingPassword(loginFormDTO.getPassword())) {
			errors.rejectValue("password", "password.incorrectpassword", "incorrect password");
			model.addAttribute("title", "Login");
			return "users/login";
		}

		setUserInSession(request.getSession(), user);

		return "redirect:";
	}

	@GetMapping("/logout")
	public String processLogoutRequest(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}
}

