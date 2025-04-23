package com.cankus.controller;

import com.cankus.dto.LessonDto;
import com.cankus.service.CourseService;
import com.cankus.service.LessonService;
import com.cankus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;
    private final UserService userService;

    public LessonController(LessonService lessonService, CourseService courseService, UserService userService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("lesson", new LessonDto());
        model.addAttribute("lessons", lessonService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("instructors", userService.getAllInstructors());
        return "lesson/lesson-create";
    }

    @PostMapping("/create")
    public String createLesson(@Valid @ModelAttribute("lesson") LessonDto lesson,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("lessons", lessonService.findAll());
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("instructors", userService.getAllInstructors());
            return "lesson/lesson-create";
        }
        /*
        Todo --> US10--> While new lesson is created,
                this lesson should assign to the all students
                who have already been enrolled to the course of this lesson.
        */
        lessonService.save(lesson);
        return "redirect:/lesson/create";

    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id,Model model){
        model.addAttribute("lesson", lessonService.findById(id));
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("instructors", userService.getAllInstructors());
        return "lesson/lesson-update";
    }

    @PostMapping("/update/{id}")
    public String updateLesson(@Valid @ModelAttribute("lesson")LessonDto lesson,
                               BindingResult bindingResult,
                               Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("instructors", userService.getAllInstructors());
            return "lesson/lesson-update";
        }
        lessonService.update(lesson);
        return "redirect:/lesson/create";
    }
}
