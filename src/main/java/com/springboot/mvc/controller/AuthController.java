package com.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.springboot.mvc.dto.RegistrationDto;
import com.springboot.mvc.models.UserEntity;
import com.springboot.mvc.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);

        return "register";

    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute RegistrationDto user, BindingResult result, Model model) {

        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());

        if (existingUserEmail != null && existingUserEmail.getEmail() != null
                && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        if (existingUserUsername != null && existingUserUsername.getUsername() != null
                && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);

        return "redirect:/clubs?success";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";

    }

}
