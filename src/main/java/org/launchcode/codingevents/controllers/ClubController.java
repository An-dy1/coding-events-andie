package org.launchcode.codingevents.controllers;

import org.hibernate.validator.constraints.ModCheck;
import org.launchcode.codingevents.data.ClubAdminRepository;
import org.launchcode.codingevents.data.ClubRepository;
import org.launchcode.codingevents.models.Club;
import org.launchcode.codingevents.models.ClubAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("clubs")
public class ClubController {

	@Autowired
	ClubRepository clubRepository;

	@Autowired
	ClubAdminRepository clubAdminRepository;

	// todo: current status 2: look for the club using a clubRepository, add that as a model attribute to the page, return club index page (a club detail page)
	@GetMapping("/{clubId}")
	public String getClubDetailPage(Model model, @PathVariable Integer clubId) {

		Optional<Club> result = clubRepository.findById(clubId);

		if (result.isPresent()) {
			model.addAttribute("club", result.get());
			model.addAttribute("title", result.get().getName());
		} else {
			model.addAttribute("title", "Club not found");
		}

		return "clubs/index";
	}

	// todo: current status 4: handler for the get request to the club admin form
	// note basic 404 page
	// I'm passing in a club as a model attribute, not an admin, because club is the parent and I set it up to cascade to the child of club admin
	// essentially, clubAdmin is now a field on the club class, this is an edit club form with only two available inputs for the club admin
	@GetMapping("/{clubId}/admin")
	public String getAddClubAdminPage(Model model, @PathVariable Integer clubId) {

		Optional<Club> currentClub = clubRepository.findById(clubId);

		if (!currentClub.isPresent()) {
			model.addAttribute("title", "Resource not found");
			return "shared/404";
		} else {
			model.addAttribute("club", currentClub.get());
			model.addAttribute("title", "Create admin for club: " + clubId);

			return "clubs/createAdmin";
		}


	}

	// todo: current status 7: if I didn't store the values of the Club fields, when I submit the form those will all be null
	// note what happens if you hover the club variable

	// todo: current status 8: given that our club is the parent entity and the club admin is a child, how do we create that one to one relationship? (what annotation, what arguments?)
	@PostMapping("/{clubId}/admin")
	public String handleClubAdminFormSubmit(@Valid @ModelAttribute Club club, Errors errors, Model model, @PathVariable Integer clubId) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Create club admin");
			// improvement: add errors to the form and fields to capture them
			return "redirect:/clubs/" + clubId;
		}

		Optional<Club> existingClub = clubRepository.findById(clubId);

		// retrieve the current club from the database, and set its clubAdmin field equal to the clubAdmin object the form created

		if (existingClub.isPresent()) {
			Club workingClub = existingClub.get();
			workingClub.setClubAdmin(club.getClubAdmin());
			clubRepository.save(workingClub);
		}


		return "redirect:/clubs/" + clubId;

	}

}
