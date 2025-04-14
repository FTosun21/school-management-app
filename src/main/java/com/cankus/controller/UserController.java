package com.cankus.controller;

import com.cankus.dto.UserDto;
import com.cankus.enums.State;
import com.cankus.service.RoleService;
import com.cankus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());
        model.addAttribute("users", userService.findAll());
        return "user/user-create"; // templates altında user folderdaki görmek istediğimiz html
    }// users parametresi html den geldi -->each teki

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") UserDto user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        //username unique olmalı
        if (userService.isEmailRegistered(user.getUserName())) {
            redirectAttributes.addFlashAttribute("error", "This email address has been already registered.");
            return "redirect:/user/create";
        }
        // password ve confirmPassword match olmalı
        if (userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", " ", "Password should match.");
        }
        // hata alınca formun yenilenmesi gerek
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            model.addAttribute("users", userService.findAll());
            return "user/user-create"; // formu aç
        }
        userService.save(user);
        return "redirect:/user/create";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable(name = "id") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());
        return "user/user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@Valid @ModelAttribute("user") UserDto user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        //Todo role kısmı user story e göre düzenlenecek

        // password ve confirmPassword match olmalı
        if (userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", " ", "Password should match.");
        }
        // hata alınca formun yenilenmesi gerek
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            return "user/user-update"; // formu aç
        }
        userService.update(user);
        return "redirect:/user/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,RedirectAttributes redirectAttributes){
        //Todo role kısmı user story e göre düzenlenecek


        userService.delete(id);
        return "redirect:/user/create";

    }


}
