package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Sponsor extends AbstractEntity {

	@NotNull(message = "name is required")
	private String name;

	private String description;

	@NotNull(message = "location is required")
	private String location;

	@OneToMany(mappedBy = "sponsor")
	private List<Event> sponsoredEvents;

	public Sponsor(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public Sponsor() {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Event> getSponsoredEvents() {
		return sponsoredEvents;
	}

	public void setSponsoredEvents(List<Event> sponsoredEvents) {
		this.sponsoredEvents = sponsoredEvents;
	}
}
