package org.launchcode.codingevents.controllers;

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
			model.addAttribute("club", clubRepository.findById(clubId).get());
		}

//		model.addAttribute(new ClubAdmin());

		return "clubs/createAdmin";
	}

	@PostMapping()
	public String handleClubAdminFormSubmit(@Valid @ModelAttribute Club club, Errors errors, Model model) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Create club admin");
			return "redirect:/events";
		}

//		Optional<Club> currentClub = clubRepository.findById(clubId);
//		if (currentClub.isPresent()) {
//			clubAdminRepository.save(clubAdmin);
//			currentClub.get().setClubAdmin(clubAdmin);
//		}

		clubRepository.save(club);

		// instead, take info from the form, save the parent and cascade to the child


//		Optional<Club> currentClub = clubRepository.findById(club.getId());
//
//		if (currentClub.isPresent()) {
//			System.out.println("current club's id is: " + currentClub.get().getId());
//		}
//
//		if (currentClub.isPresent()) {
//			currentClub.get().setClubAdmin(clubAdmin);
////			clubRepository.save(currentClub.get());
//		}
//
//		currentClub.get().setClubAdmin(clubAdmin);
//		clubRepository.save(currentClub.get());

//		club.setClubAdmin(clubAdmin);


		return "redirect:/events";

	}
}
