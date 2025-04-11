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
}
    /*
        1- UserService içerisinde method oluştur
        2- UserServiceİmpl içinde isEmailRegistered() oluştur
        3- UserController --> username unique olmalı  if condition ile db kontrol için error oluştur
        4- UserService --> isPasswordMatched() oluştur
        5- UserServiceImpl--> isPasswordMatched() methodu çağır
        6- UserController --> // password ve confirmPassword match olmalı if cond.
        7- UserController--> // hata alınca formun yenilenmesi gerek
        8- UserService   -->  kullanıcıyı kaydet save()
        9- UserServiceImpl -->    kullanıcıyı kaydet save() --> To do security implement edilirken yeniden dön
        10 UserController--> userService.save(user);--> UserController return "redirect:/user/create";
        11-Role için converter yapısı önemli
        12- --> RoleService --> findById() ekle
        13- --> UserServiceImpl git findById() tamamla
        14- --> RoleDtoConverter class--> convert() yap

     */

/*  controllerda getCreatePage() ile tüm user listi için
    1- repository --> list için default methodlar yeterli
    2- service interface içinde listeleme methodu gerek
    3- serviceimpl -->  find all method oluşturulacak
    4- UserMapper-->findAll method için userMapper içinde ModelMapper objesi oluşturmak gerek
    5- Main method --> modelMapper için Bean annotation ile ModelMapper bean oluştur
    6- UserMapper --> private final ModelMapper modelMapper; oluşturuldu ve // DTO -> Entity // Entity -> DTO mthodlar
    7- iplementation --> findAll() meythod ve //lambda expression method refrains  çalış
    8- userController-->  getCreatePage() metghodu tamamla
 */