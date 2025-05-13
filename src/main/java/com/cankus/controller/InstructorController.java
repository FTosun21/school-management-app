package com.cankus.controller;

import com.cankus.dto.AssessmentDto;
import com.cankus.service.AssessmentService;
import com.cankus.service.LessonStudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private final LessonStudentService lessonStudentService;
    private final AssessmentService assessmentService;

    public InstructorController(LessonStudentService lessonStudentService, AssessmentService assessmentService) {
        this.lessonStudentService = lessonStudentService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/students")
    public String getAllStudentsWithThisInstructor( Model model) {
        model.addAttribute("studentLessons", lessonStudentService.getLessonStudentsByInstructorId(4L));
        return "instructor/general-assessment";
    }
    @GetMapping("/students/{lessonStudentId}")
    public String getAssessStudentPage(Model model,@PathVariable Long lessonStudentId){
        model.addAttribute("lessonStudent",lessonStudentService.findById(lessonStudentId));
        model.addAttribute("assessment", assessmentService.getAssessmentDtoWithLessonStudent(lessonStudentId));
        model.addAttribute("grades",assessmentService.findAllByLessonStudentId(lessonStudentId));
        return "instructor/assess-student";
    }
    @PostMapping("/students/{lessonStudentId}")
    public String assessStudent(@Valid @ModelAttribute("assessment")
                                    AssessmentDto assessment, RedirectAttributes redirectAttributes,
                                @PathVariable Long lessonStudentId,Model model){
        if(assessment.getGrade()==null || assessment.getInstructorImpressionOfStudent().isBlank()){
            redirectAttributes.addFlashAttribute("error","You could not assess your student successfully.");
            return "redirect:/instructor/students/" + lessonStudentId;
        }
        assessmentService.save(assessment,lessonStudentId);
        return "redirect:/instructor/students";
    }

}
