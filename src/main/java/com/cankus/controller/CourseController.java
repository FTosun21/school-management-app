package com.cankus.controller;

import com.cankus.dto.CourseDto;
import com.cankus.service.CourseService;
import com.cankus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;


    public CourseController(CourseService courseService, UserService userService, UserService userService1) {
        this.courseService = courseService;
        this.userService = userService1;
    }

    @GetMapping("/create")
    public String getCreatePage(Model model){
        model.addAttribute("course",new CourseDto());
        model.addAttribute("managers",userService.getAllManagers());
        model.addAttribute("courses",courseService.findAll());
        return "course/course-create";
    }

    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") CourseDto course,
                               BindingResult bindingResult,
                               //RedirectAttributes redirectAttributes,
                               Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("courses",courseService.findAll());
            model.addAttribute("managers",userService.getAllManagers());
            return "course/course-create";
        }
        //Todo --> US6 AC.--> While new course is created, newly created course should assign to all students with isEnrolled false.

        courseService.save(course);
        return  "redirect:/course/create";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable(name = "id") Long courseId,Model model){
        model.addAttribute("course",courseService.findById(courseId));
        model.addAttribute("managers",userService.getAllManagers());
        return "course/course-update";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@Valid @ModelAttribute("course") CourseDto course,
                               BindingResult bindingResult,
                               //RedirectAttributes redirectAttributes,
                               Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("managers",userService.getAllManagers());
            return "course/course-update";
        }

        courseService.update(course);
        return "redirect:/course/create";
    }

}
