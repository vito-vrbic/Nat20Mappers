package com.ttrpg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class); //
    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username) {
        logger.info("getting profile of {}", username); // Bilježi pokušaj prijave u log
        return "forward:/index.html";
    }
}
