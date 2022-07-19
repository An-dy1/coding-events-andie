package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Entity
public class Tag extends AbstractEntity {

	@Size(min = 1, max = 25)
	@NotBlank
	private String name;

	// tells Hibernate that it should look for events that have the given tag object associated with it in the tags field in the particular class
	@ManyToMany(mappedBy = "tags")
	private List<Event> events = new ArrayList<>();

	public Tag(String name) {
		this.name = name;
	}

	public Tag() {
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return "#" + name + " ";
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Event> getEvents() {
		return events;
	}
}
