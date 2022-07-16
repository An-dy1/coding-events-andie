package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class ClubAdmin extends AbstractEntity {

	@NotNull(message = "name is required")
	private String name;

	@Email(message = "must enter valid email")
	private String email;

	@NotNull(message = "must select membership level")
	private String membershipLevel;

	// todo current status 9: if we wanted our admin to know about its related club, how would we do that?

	public ClubAdmin() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMembershipLevel() {
		return membershipLevel;
	}

	public void setMembershipLevel(String membershipLevel) {
		this.membershipLevel = membershipLevel;
	}

}
