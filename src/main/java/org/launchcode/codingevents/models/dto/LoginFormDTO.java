package org.launchcode.codingevents.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// todo: what's the value of a DTO here, rather than binding to a User object directly in the form?
public class LoginFormDTO {

	// todo: reminder on difference between not null and not blank?
	@NotNull
	@NotBlank
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;

	@NotNull
	@NotBlank
	@Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters")
	private String password;

	// todo: doesn't need a constructor?

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
