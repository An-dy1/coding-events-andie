package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
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

	@ManyToOne
	private Club hostingClub;

	@ManyToOne
	private Sponsor sponsor;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	private EventDetails eventDetails;

	public Event(String name, EventCategory category, Club club) {
		this.name = name;
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

	public EventDetails getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
	}

	@Override
	public String toString() {
		return name;
	}


}
