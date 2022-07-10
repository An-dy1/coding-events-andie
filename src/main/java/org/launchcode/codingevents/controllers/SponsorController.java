package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.SponsorRepository;
import org.launchcode.codingevents.models.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("sponsor")
public class SponsorController {

	@Autowired
	private SponsorRepository sponsorRepository;

	@GetMapping("/{sponsorId}")
	public String getSponsorDetailPage(@PathVariable Integer sponsorId, Model model) {

		Optional<Sponsor> currentSponsor = sponsorRepository.findById(sponsorId);

		if (currentSponsor.isPresent()) {
			model.addAttribute("title", currentSponsor.get().getName());
			model.addAttribute("sponsor", currentSponsor.get());
		} else {
			model.addAttribute("title", "Cannot find sponsor");
		}


		return "sponsors/detail";
	}
}
