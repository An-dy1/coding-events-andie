package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Club extends AbstractEntity {

	@NotNull(message = "name is required")
	private String name;

	private String description;

	@NotNull(message = "location is required")
	private String location;

	@OneToMany(mappedBy = "hostingClub")
	private final List<Event> events = new ArrayList<>();

	public Club(String name, String location, String description) {
		this.name = name;
		this.location = location;
	}

	public Club() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public List<Event> getEvents() {
		return events;
	}
}
