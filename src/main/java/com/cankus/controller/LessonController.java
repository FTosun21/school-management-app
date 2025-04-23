package com.cankus.controller;

import com.cankus.dto.LessonDto;
import com.cankus.service.CourseService;
import com.cankus.service.LessonService;
import com.cankus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lesson")
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
    public String getCreatePage(Model model){
        model.addAttribute("lesson",new LessonDto());
        model.addAttribute("lessons",lessonService.findAll());
        model.addAttribute("courses",courseService.findAll());
        model.addAttribute("instructors",userService.getAllInstructors());
        return "lesson/lesson-create";
    }

    @PostMapping("/create")
    public String createLesson(@Valid @ModelAttribute("lesson") LessonDto lesson,
                               BindingResult bindingResult,
                               Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("lessons",lessonService.findAll());
            model.addAttribute("courses",courseService.findAll());
            model.addAttribute("instructors",userService.getAllInstructors());
            return "lesson/lesson-create";
        }
        lessonService.save(lesson);
        return "redirect:/lesson/create";

    }
}
