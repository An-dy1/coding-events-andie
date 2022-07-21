package org.launchcode.codingevents.models.dto;

public class RegistrationFormDTO extends LoginFormDTO {

	private String verifyPassword;

	public String getVerifyPassword() {
		return verifyPassword;
	}

	// todo note: no validation on whether this matches the LoginForm password field or not
	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}
}
