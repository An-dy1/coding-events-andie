package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

	@NotNull
	private String username;

	@NotNull
	private String passwordHash;

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public User(String username, String password) {
		this.username = username;
		this.passwordHash = encoder.encode(password);
	}

	public boolean isMatchingPassword(String password) {
		return encoder.matches(password, passwordHash);
	}
}
