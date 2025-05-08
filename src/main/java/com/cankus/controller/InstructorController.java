package com.cankus.controller;

import com.cankus.service.LessonStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private final LessonStudentService lessonStudentService;

    public InstructorController(LessonStudentService lessonStudentService) {
        this.lessonStudentService = lessonStudentService;
    }

    @GetMapping("/students")
    public String getAllStudentsWithThisInstructor( Model model) {
        model.addAttribute("studentLessons", lessonStudentService.getLessonStudentsByInstructorId(4L));
        return "instructor/general-assessment";
    }

}
