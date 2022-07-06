package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.ClubRepository;
import org.launchcode.codingevents.models.Club;
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
		}

		return "clubs/index";
	}
}
