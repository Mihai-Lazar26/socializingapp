package com.example.socializingapp.controllers;

import com.example.socializingapp.entities.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String homePage(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return "redirect:/profile";

        return "home";
    }

    @GetMapping("/signin")
    public String signInPage(
            @RequestParam(required = false) Integer error,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return "redirect:/profile";

        if (error != null)
            model.addAttribute("invalidSignIn", "Invalid username or password!");
        return "signin";
    }

    @GetMapping("/signup")
    public String signUpPage(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return "redirect:/profile";

        model.addAttribute("userEntity", new User());
        return "signup";
    }

    @GetMapping("/profile")
    public String signOut(
            Model model
    ) {
        return "profile";
    }
}