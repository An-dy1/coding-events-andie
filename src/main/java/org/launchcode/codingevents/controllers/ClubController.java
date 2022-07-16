package org.launchcode.codingevents.controllers;

import org.hibernate.validator.constraints.ModCheck;
import org.launchcode.codingevents.data.ClubAdminRepository;
import org.launchcode.codingevents.data.ClubRepository;
import org.launchcode.codingevents.models.Club;
import org.launchcode.codingevents.models.ClubAdmin;
import org.launchcode.codingevents.models.dto.ClubClubAdminDTO;
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

	// todo 2: instead of passing in a club object directly to our club admin form, we can set a club on the clubadminDto and then pass that into the form
	// note: 404 code was written on other branch and this is not doing much validation atm
	@GetMapping("/{clubId}/admin")
	public String getAddClubAdminPage(Model model, @PathVariable Integer clubId) {

		Optional<Club> result = clubRepository.findById(clubId);
		Club club = result.get();

		ClubClubAdminDTO clubAndAdmin = new ClubClubAdminDTO();
		clubAndAdmin.setClub(club);

		model.addAttribute("clubAndAdmin", clubAndAdmin);

		model.addAttribute("title", "Create admin for club: " + clubId);


		return "clubs/createAdmin";
	}

	@PostMapping("/{clubId}/admin")
	public String handleClubAdminFormSubmit(@Valid @ModelAttribute ClubClubAdminDTO clubAndAdminDTO, Errors errors, Model model, @PathVariable Integer clubId) {

		if (errors.hasErrors()) {
			model.addAttribute("title", "Create club admin");
			return "redirect:/clubs/" + clubAndAdminDTO.getClub().getId();
		}

		Club club = clubAndAdminDTO.getClub();
		ClubAdmin clubAdmin = clubAndAdminDTO.getAdmin();

		club.setClubAdmin(clubAdmin);
		clubRepository.save(club);

		return "redirect:/clubs/" + clubId;

	}

}
