package com.example.demo.web.controllers;

import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    WindRepository windRepository;

    @Autowired
    UserService userService;

    public void findAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());
        model.addAttribute("items",itemRepository.findAll());
    }

    @RequestMapping("/login")
    public String login(Model model) {
        findAll(model);
        return "login";
    }

    @RequestMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        findAll(model);
        if (userService.getUser() != null) {
            model.addAttribute("user", userService.getUser());
        }
        return "profile";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        findAll(model);
        model.addAttribute("page_title", "New User Registration");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result,
                                          Model model) {
        findAll(model);
        model.addAttribute("page_title", "Update Profile");
        if (result.hasErrors()) {
            return "register";
        } else {
            //Update User and Admin
            Boolean isUser = userRepository.existsById(user.getId());
            if (isUser) {
                //updating with existed username
                if (userRepository.findByUsername(user.getUsername()) != null &&
                        //current user
                        !userRepository.findByUsername(user.getUsername()).equals(user)) {
                    model.addAttribute("message",
                            "We already have a username called " + user.getUsername() + "!" + " Try something else.");
                    return "register";
                }

                User userInDB = userRepository.findById(user.getId()).get();
                userInDB.setFirstName(user.getFirstName());
                userInDB.setLastName(user.getLastName());
                userInDB.setEmail(user.getEmail());
                userInDB.setUsername(user.getUsername());
                userInDB.setPassword(userService.encode(user.getPassword()));
                userRepository.save(userInDB);
                model.addAttribute("message", "User Account Successfully Updated");
            }
            //New User
            else {
                //Registering with existed username
                if (userRepository.findByUsername(user.getUsername()) != null) {
                    model.addAttribute("message",
                            "We already have a username called " + user.getUsername() + "!" + " Try something else.");
                    return "register";
                } else {
                    user.setPassword(userService.encode(user.getPassword()));
                    userService.saveUser(user);
                    model.addAttribute("message", "User Account Successfully Created");
                }
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/updateUser")
    public String viewUser(Model model,
                           HttpServletRequest request,
                           Authentication authentication,
                           Principal principal) {
//        String username = principal.getName();
        model.addAttribute("page_title", "Update Profile");
        model.addAttribute("user", userService.getUser());
        return "register";
    }
}
