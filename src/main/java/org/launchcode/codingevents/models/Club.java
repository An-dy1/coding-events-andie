package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Club extends AbstractEntity {

	@NotNull(message = "club name is required")
	private String name;

	private String description;

	@NotNull(message = "location is required")
	private String location;

	public Club(String name, String location) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
