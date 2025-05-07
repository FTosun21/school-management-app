package com.cankus.controller;

import com.cankus.dto.StudentDto;
import com.cankus.enums.State;
import com.cankus.service.CourseStudentService;
import com.cankus.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final CourseStudentService courseStudentService;

    public StudentController(StudentService studentService, CourseStudentService courseStudentService) {
        this.studentService = studentService;
        this.courseStudentService = courseStudentService;
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("student", new StudentDto());
        model.addAttribute("states", State.values());
        model.addAttribute("students", studentService.findAll());
        return "student/student-create";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") StudentDto student,
                                BindingResult bindingResult,
                                Model model) {
        //Todo While saving student, assign all available courses to this student with status false.
        if (bindingResult.hasErrors()) {
            model.addAttribute("states", State.values());
            model.addAttribute("students", studentService.findAll());
            return "student/student-create";
        }
        studentService.save(student);
        return "redirect:/student/create";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("states", State.values());
        return "student/student-update";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@Valid @ModelAttribute("student") StudentDto student,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("states", State.values());
            return "student/student-update";
        }
        studentService.update(student);
        return "redirect:/student/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/student/create";
    }

    @GetMapping("/assign/{id}")
    public String getAllStudent(@PathVariable Long id, Model model) {
        model.addAttribute("studentCourses",courseStudentService.findAllByStudentId(id));
        return "student/student-courses";
    }

    @GetMapping("/enroll/{courseStudentId}/{id}")
    public String enrollCourse(@PathVariable Long courseStudentId,@PathVariable Long id){
       courseStudentService.enroll(courseStudentId);
        return "redirect:/student/assign/" + id; // Refresh assigned courses list

    }

    @GetMapping("/drop/{courseStudentId}/{id}")
    public String dropCourse(@PathVariable Long courseStudentId,@PathVariable Long id){
        courseStudentService.drop(courseStudentId);
        return "redirect:/student/assign/" + id; // Refresh assigned courses list

    }



}
