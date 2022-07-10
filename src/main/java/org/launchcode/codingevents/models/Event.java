package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chris Bay
 */
@Entity
public class Event extends AbstractEntity {

	@ManyToOne
	@NotNull(message = "category is required")
	private EventCategory eventCategory;

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;

	@Size(max = 500, message = "Description too long!")
	private String description;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email. Try again.")
	private String contactEmail;

	@ManyToOne
	private Club hostingClub;

	@ManyToOne
	private Sponsor sponsor;

	public Event(String name, String description, String contactEmail, EventCategory category, Club club) {
		this.name = name;
		this.description = description;
		this.contactEmail = contactEmail;
		this.eventCategory = category;
		this.hostingClub = club;
	}

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}

	public Club getHostingClub() {
		return hostingClub;
	}

	public void setHostingClub(Club hostingClub) {
		this.hostingClub = hostingClub;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@Override
	public String toString() {
		return name;
	}


}
