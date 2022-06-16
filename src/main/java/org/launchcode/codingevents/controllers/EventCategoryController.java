package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("event-categories")
public class EventCategoryController {

	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@GetMapping
	public String displayAllEvents(Model model) {
		model.addAttribute("title", "All Categories");
		model.addAttribute("categories", eventCategoryRepository.findAll());
		return "eventCategories/index";
	}

	@GetMapping("create")
	public String renderCreateEventCategory(Model model) {
		model.addAttribute(new EventCategory());
		model.addAttribute("title", "Create Event Category");
		return "eventCategories/create";
	}

	@PostMapping("create")
	public String processCreateEventCategory(Model model, @ModelAttribute @Valid EventCategory eventCategory, Errors errors) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Create Event Category");
			return "eventCategories/create";
		} else {
			model.addAttribute("title", "All categories");
			eventCategoryRepository.save(eventCategory);
			return "redirect:";
		}

	}
}
