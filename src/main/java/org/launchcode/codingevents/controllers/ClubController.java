package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.ClubAdminRepository;
import org.launchcode.codingevents.data.ClubRepository;
import org.launchcode.codingevents.models.Club;
import org.launchcode.codingevents.models.ClubAdmin;
import org.launchcode.codingevents.models.dto.ClubClubAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("clubs")
public class ClubController {

	@Autowired
	ClubRepository clubRepository;

	@Autowired
	ClubAdminRepository clubAdminRepository;

	@GetMapping()
	public String getClubDetailPage(Model model, @RequestParam Integer clubId) {

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
	@GetMapping("/admin")
	public String getAddClubAdminPage(Model model, @RequestParam Integer clubId) {

		Optional<Club> result = clubRepository.findById(clubId);
		Club club = result.get();

		ClubClubAdminDTO clubAndAdmin = new ClubClubAdminDTO();
		clubAndAdmin.setClub(club);

		model.addAttribute("clubAndAdmin", clubAndAdmin);
		model.addAttribute("title", "Create admin for club: " + clubId);


		return "clubs/createAdmin";
	}

	@PostMapping("/admin")
	public String handleClubAdminFormSubmit(@RequestParam Integer clubId, @ModelAttribute @Valid ClubClubAdminDTO clubAndAdmin, Model model, Errors errors) {

		System.out.println("got to the post method");
		if (errors.hasErrors()) {
			System.out.println("has errors");
			System.out.println(errors.getAllErrors());
			model.addAttribute("title", "Create admin for club: " + clubAndAdmin.getClub().getId());

			// the model *should* already have any models that were bound (our ClubAdminDTO) and Errors - but that isn't happening
			// because of a redirect? Because the clubAdminDTO isn't an entity itself?
//			model.addAttribute("errors", errors);
//			model.addAttribute("clubAndAdmin", clubAndAdmin);

			return "redirect:/clubs/admin?clubId=" + clubAndAdmin.getClub().getId();


		} else {
			System.out.println("Does not have errors");
			Club club = clubAndAdmin.getClub();
			ClubAdmin clubAdmin = clubAndAdmin.getAdmin();

			club.setClubAdmin(clubAdmin);
			clubRepository.save(club);

			return "redirect:/clubs?clubId=" + clubAndAdmin.getClub().getId();
		}


	}

	@GetMapping("/admin/delete")
	public String handleDeleteAdmin(@RequestParam() Integer clubId) {

		Optional<Club> result = clubRepository.findById(clubId);

		if (result.isPresent()) {
			Club club = result.get();
			if (club.getClubAdmin() != null) {
				Integer adminId = club.getClubAdmin().getId();
				club.setClubAdmin(null);

				clubAdminRepository.deleteById(adminId);
				clubRepository.save(club);
			}

		}


		return "redirect:/clubs?clubId=" + clubId;
	}

}
