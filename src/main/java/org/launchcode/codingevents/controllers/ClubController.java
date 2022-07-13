package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.ClubRepository;
import org.launchcode.codingevents.models.Club;
import org.launchcode.codingevents.models.ClubAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("clubs")
public class ClubController {

	@Autowired
	ClubRepository clubRepository;

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

		model.addAttribute("title", "Create admin for club: " + clubId);
		model.addAttribute(new ClubAdmin());

		return "clubs/createAdmin";
	}
}
