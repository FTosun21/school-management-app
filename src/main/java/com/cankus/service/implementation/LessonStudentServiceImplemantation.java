package com.cankus.service.implementation;

import com.cankus.entity.CourseStudent;
import com.cankus.entity.Lesson;
import com.cankus.entity.LessonStudent;
import com.cankus.entity.Student;
import com.cankus.repository.LessonStudentRepository;
import com.cankus.service.LessonStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonStudentServiceImplemantation implements LessonStudentService{

    private final LessonStudentRepository lessonStudentRepository;

    public LessonStudentServiceImplemantation(LessonStudentRepository lessonStudentRepository) {
        this.lessonStudentRepository = lessonStudentRepository;
    }

    @Transactional
    @Override
    public void assignLessonToStudents(Lesson lesson, List<CourseStudent> students) {
        List<LessonStudent> lessonStudents = students.stream()
                .filter(courseStudent -> !courseStudent.getCourse().isDeleted() && !courseStudent.getStudent().isDeleted())
                .map(courseStudent -> {
                    LessonStudent lessonStudent = new LessonStudent();
                    lessonStudent.setLesson(lesson);
                    lessonStudent.setStudent(courseStudent.getStudent());
                    return lessonStudent;
                })
                .collect(Collectors.toList());

        // Save all lesson assignments in a batch operation for performance
        lessonStudentRepository.saveAll(lessonStudents);
    }


}
