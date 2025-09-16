package com.springboot.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.springboot.mvc.dto.ClubDto;
import com.springboot.mvc.models.Club;
import com.springboot.mvc.models.UserEntity;
import com.springboot.mvc.security.SecurityUtil;
import com.springboot.mvc.service.ClubService;
import com.springboot.mvc.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class ClubController {

    private final ClubService clubService;
    private final UserService userService;

    @GetMapping("/clubs")
    public String listClubs(Model model) {

        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.getAllClubs();
        String username = SecurityUtil.getSessionUser();

        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);

        return "clubs-list";

    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {

        Club club = new Club();
        model.addAttribute("club", club);

        return "clubs-create";

    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }

        clubService.saveClub(clubDto);
        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable Long clubId, Model model) {

        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);

        return "clubs-edit";

    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable Long clubId, @Valid @ModelAttribute("club") ClubDto club,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-edit";
        }

        club.setId(clubId);
        clubService.updateClub(club);

        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable Long clubId, Model model) {

        UserEntity user = new UserEntity();
        ClubDto clubDto = clubService.findClubById(clubId);
        String username = SecurityUtil.getSessionUser();

        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("user", user);
        model.addAttribute("club", clubDto);

        return "clubs-detail";

    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable Long clubId) {

        clubService.delete(clubId);

        return "redirect:/clubs";

    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam("query") String query, Model model) {

        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);

        return "clubs-list";

    }

}
