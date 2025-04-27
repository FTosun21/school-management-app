package com.cankus.controller;

import com.cankus.dto.StudentDto;
import com.cankus.enums.State;
import com.cankus.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("student", new StudentDto());
        model.addAttribute("states", State.values());
        model.addAttribute("students",studentService.findAll());
        return "student/student-create";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student")StudentDto student,
                                BindingResult bindingResult,
                                Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("states",State.values());
            model.addAttribute("students",studentService.findAll());
            return "student/student-create";
        }
        studentService.save(student);
        return "redirect:/student/create";
    }

}
