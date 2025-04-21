package com.cankus.controller;

import com.cankus.dto.CourseDto;
import com.cankus.service.CourseService;
import com.cankus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes){
        /** TODO US8: Before deleting a course, check if it has lessons.
                If lessons exist, do not delete and show message: "This Course has either one or more than one lessons. Not allowed to delete".
                If no lessons, first remove course from all students (courseStudent), then soft-delete course.
                On successful delete, show message: "This Course is successfully deleted".
         */
        courseService.delete(id);
        return "redirect:/course/create";
    }

}
