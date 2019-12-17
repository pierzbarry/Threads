package com.example.demo.web.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.FormAttributes;
import com.example.demo.business.services.UserService;
import com.example.demo.web.config.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    WindRepository windRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    UserService userService;

    @ModelAttribute("categories")
    public Iterable<Category> populateCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("climates")
    public Iterable<Climate> populateClimate() {
        return climateRepository.findAll();
    }

    @ModelAttribute("occasions")
    public Iterable<Occasion> populateOccasion() {
        return occasionRepository.findAll();
    }

    @ModelAttribute("winds")
    public Iterable<Wind> populateWind() {
        return windRepository.findAll();
    }

    @RequestMapping("/")
    public String listItems(Model model, @RequestParam(defaultValue = "0") int page) {

        User user = userService.getUser();

        if (user != null) {
            model.addAttribute("formAttributes", new FormAttributes());
            model.addAttribute("items",itemRepository.findAll());
            if (userService.isUser()) {
                model.addAttribute("items", itemRepository.findAllByUser(user));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", itemRepository.findAll());
            }
        } else {
            // if user is not logged in only show example set of items (first four)
            model.addAttribute("items", itemRepository.findAll(PageRequest.of(page, 4)));
        }

        //model.addAttribute("searchString", null);
        model.addAttribute("currentPage", page);
        return "list";
    }

    @GetMapping("/search")
    public String searchword(Model model,
                             @RequestParam String search,
                             @RequestParam(defaultValue = "0") int page) {

        User user = userService.getUser();
        Object searchItems =
                itemRepository.findAllByNameContainingOrDescriptionContainingAllIgnoreCase(search, search, PageRequest.of(page, 4));
        if (user != null) {
            model.addAttribute("formAttributes", new FormAttributes());
            if (userService.isUser()) {
                model.addAttribute("items",
                        itemRepository
                                .findAllByUserAndNameContainingOrUserAndDescriptionContainingAllIgnoreCase(user, search, user, search, PageRequest.of(page, 4)));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", searchItems);
            }
        } else {
            model.addAttribute("items", searchItems);
        }
        model.addAttribute("searchString", search);
        model.addAttribute("currentPage", page);
        return "list";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @GetMapping("/add")
    public String itemForm(Model model) {
        model.addAttribute("imageLabel", "Upload Image");
        model.addAttribute("myuser", userService.getUser());
        model.addAttribute("item", new Item());
        return "itemform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("item") Item item,
                              BindingResult result,
                              @RequestParam("file") MultipartFile file,
                              Model model) {
        model.addAttribute("imageLabel", "Upload Image");
        model.addAttribute("myuser", userService.getUser());
        if (result.hasErrors()) {
            for (Object objectError : result.getAllErrors()) {
                System.err.println(objectError);
            }
            return "itemform";
        }
        if (!file.isEmpty()) {
            try {
                Map uploadResultMap = cloudc.upload(
                        file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                String url = uploadResultMap.get("url").toString();
                String uploadedName = uploadResultMap.get("public_id").toString();
                String transformedImage = cloudc.createUrl(uploadedName, 150, 150);
                item.setPicturePath(transformedImage);
                item.setUser(userService.getUser());
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/add";
            }
        } else {
            //if file is empty and there is a picture path then save item
            if (item.getPicturePath().isEmpty()) {
                return "itemform";
            }
        }
        itemRepository.save(item); //generate SQL statement and insert into database
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id,
                             @ModelAttribute Item item,
                             Model model) {
        item = itemRepository.findById(id).get();
        model.addAttribute("myuser", userService.getUser());
        model.addAttribute("item", itemRepository.findById(id).get());

        if (item.getPicturePath().isEmpty()) {
            model.addAttribute("imageLabel", "Upload Image");
        } else {
            model.addAttribute("imageLabel", "Upload New Image");
        }
        return "itemform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id) {
        itemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/check")
    public String check(@RequestParam("check") long[] ids,
                        @RequestParam("name") String name,
                        Model model) {
        // delete selected
        if (name.equals("delete")) {
            for (long id : ids) {
                itemRepository.deleteById(id);
            }
            return "redirect:/";
        }
        //packing list
        if (name.equals("packing")) {
            HashSet items = new HashSet<Item>();
            for (long id : ids) {
                items.add(itemRepository.findById(id).get());
            }
            model.addAttribute("page_title", "Packing List");
            model.addAttribute("items", items);
            return "detaillist";
        }
        return "list";
    }

    @GetMapping("/about")
    public String getAbout(Model model) {
        return "about";
    }

    @GetMapping("/status")
    public String getStatus(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());
        return "status";
    }
}
