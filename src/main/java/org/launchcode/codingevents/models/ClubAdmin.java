package org.launchcode.codingevents.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ClubAdmin extends AbstractEntity {

	@NotNull(message = "name is required")
	@Size(min = 3)
	private String name;

	@Email(message = "must enter valid email")
	private String email;

	@NotNull(message = "must select membership level")
	private String membershipLevel;

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

//	public Club getAssociatedClub() {
//		return associatedClub;
//	}
//
//	public void setAssociatedClub(Club associatedClub) {
//		this.associatedClub = associatedClub;
//	}
}
