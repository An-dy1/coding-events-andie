package org.launchcode.codingevents.models.dto;

import org.launchcode.codingevents.models.Club;
import org.launchcode.codingevents.models.ClubAdmin;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// todo 1: data transfer object
// why? in my example, it is going to hold a Club object so we don't have to keep track of individual fields
// process/validate two related pieces of information at once rather than separately
// but no entity annotation: we aren't persisting this, we're persisting values inside it
public class ClubClubAdminDTO {

	@Valid
	private Club club;

	@Valid
	private ClubAdmin admin;

	public ClubClubAdminDTO() {
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public ClubAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(ClubAdmin admin) {
		this.admin = admin;
	}
}
