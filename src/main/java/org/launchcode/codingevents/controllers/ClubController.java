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

	@GetMapping("/{clubId}/admin")
	public String getAddClubAdminPage(Model model, @PathVariable Integer clubId) {

		Optional<Club> currentClub = clubRepository.findById(clubId);

		model.addAttribute("title", "Create admin for club: " + clubId);

		if (currentClub.isPresent()) {
			model.addAttribute("club", currentClub.get());
		}

		return "clubs/createAdmin";
	}

	@PostMapping("/{clubId}/admin")
	public String handleClubAdminFormSubmit(@Valid @ModelAttribute Club club, Errors errors, Model model, @PathVariable Integer clubId) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Create club admin");
			return "redirect:/clubs/" + clubId;
		}

		Optional<Club> existingClub = clubRepository.findById(clubId);

		if (existingClub.isPresent()) {
			Club workingClub = existingClub.get();
			workingClub.setClubAdmin(club.getClubAdmin());
			clubRepository.save(workingClub);
		}


		return "redirect:/clubs/" + clubId;

	}

}
