package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.*;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private SponsorRepository sponsorRepository;

	@Autowired
	private TagRepository tagRepository;

	@GetMapping
	public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {
		if (categoryId == null) {
			model.addAttribute("title", "All Events");
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("sponsors", sponsorRepository.findAll());
		} else {
			// get the event category by the passed in request param
			// if nothing is returned then show all
			Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);

			if (result.isEmpty()) {
				model.addAttribute("title", "Could not find category: " + categoryId);
			} else {
				EventCategory category = result.get();
				model.addAttribute("title", "Events in category " + category.getName());
				model.addAttribute("events", category.getEvents());
			}
		}

		return "events/index";
	}

	@GetMapping
	public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {

		if (categoryId == null) {
			model.addAttribute("title", "All Events");
			model.addAttribute("events", eventRepository.findAll());
		} else {
			Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
			if (result.isEmpty()) {
				model.addAttribute("title", "Invalid Category ID: " + categoryId);
			} else {
				EventCategory category = result.get();
				model.addAttribute("title", "Events in category: " + category.getName());
				model.addAttribute("events", category.getEvents());
			}
		}

		return "events/index";
	}

	@GetMapping("create")
	public String displayCreateEventForm(Model model) {
		model.addAttribute("title", "Create Event");
		model.addAttribute(new Event());
		model.addAttribute("categories", eventCategoryRepository.findAll());
		model.addAttribute("clubs", clubRepository.findAll());
		model.addAttribute("sponsors", sponsorRepository.findAll());

		return "events/create";
	}

	@PostMapping("create")
	public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
	                                     Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("title", "Create Event");
			return "events/create";
		}

		eventRepository.save(newEvent);
		return "redirect:";
	}

	@GetMapping("delete")
	public String displayDeleteEventForm(Model model) {
		model.addAttribute("title", "Delete Events");
		model.addAttribute("events", eventRepository.findAll());
		return "events/delete";
	}

	@PostMapping("delete")
	public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

		if (eventIds != null) {
			for (int id : eventIds) {
				eventRepository.deleteById(id);
			}
		}

		return "redirect:";
	}

	@GetMapping("detail")
	public String displayEventDetails(@RequestParam Integer eventId, Model model) {

		Optional<Event> result = eventRepository.findById(eventId);


		if (result.isEmpty()) {
			model.addAttribute("title", "Invalid Event ID: " + eventId);
		} else {
			Event event = result.get();
			model.addAttribute("title", event.getName() + " Details");
			model.addAttribute("event", event);
			model.addAttribute("tags", event.getTags());
		}

		return "events/detail";
	}

	@GetMapping("add-tag")
	public String displayAddTagToEventForm(@RequestParam Integer eventId, Model model) {

		Optional<Event> result = eventRepository.findById(eventId);
		Event event = result.get();

		model.addAttribute("title", "Add tags to event " + event.getName());
		model.addAttribute("tags", tagRepository.findAll());

		// form needs to know which event to add the tag to
		EventTagDTO eventTag = new EventTagDTO();
		eventTag.setEvent(event);

		// allow us to validate a single object, instance of EventTagDTO
		model.addAttribute("eventTag", eventTag);


		return "events/add-tag";
	}

	@PostMapping("add-tag")
	public String handleAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Model model, Errors errors) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Sorry, could not add tag, please try again");
			return "redirect:/add-tag";
		}

		Event event = eventTag.getEvent();
		Tag tag = eventTag.getTag();

		if (!event.getTags().contains(tag)) {
			event.addTag(tag);
			eventRepository.save(event);
		}

		return "redirect:/events/detail?eventId=" + event.getId();

	}

}
