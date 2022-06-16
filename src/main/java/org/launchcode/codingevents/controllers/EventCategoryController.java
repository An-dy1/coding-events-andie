package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("event-categories")
public class EventCategoryController {

	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@GetMapping
	public String displayAllEvents() {
		return "string";
	}
}
