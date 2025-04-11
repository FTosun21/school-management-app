package com.cankus.controller;

import com.cankus.dto.UserDto;
import com.cankus.enums.State;
import com.cankus.service.RoleService;
import com.cankus.service.UserService;
import jakarta.validation.Valid;
import lombok.val;
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

    @GetMapping ("/create")
    public String getCreatePage(Model model){
        model.addAttribute("user",new UserDto());
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("states", State.values());
        model.addAttribute("users",userService.findAll());
        return "user/user-create" ; // templates altında user folderdaki görmek istediğimiz html
    }// users parametresi html den geldi -->each teki



}
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