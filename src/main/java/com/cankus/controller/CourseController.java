package com.cankus.controller;

import com.cankus.dto.CourseDto;
import com.cankus.service.CourseService;
import com.cankus.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;


    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
    }

    @GetMapping("/create")
    public String getCreatePage(Model model){
        model.addAttribute("course",new CourseDto());
        model.addAttribute("courses",courseService.findAll());
        return "course/course-create";
    }
}
