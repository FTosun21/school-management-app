package com.cankus.service.implementation;

import com.cankus.dto.LessonStudentDto;
import com.cankus.entity.CourseStudent;
import com.cankus.entity.Lesson;
import com.cankus.entity.LessonStudent;
import com.cankus.mapper.LessonStudentMapper;
import com.cankus.repository.LessonStudentRepository;
import com.cankus.service.LessonStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LessonStudentServiceImplemantation implements LessonStudentService {

    private final LessonStudentRepository lessonStudentRepository;
    private final LessonStudentMapper lessonStudentMapper;

    public LessonStudentServiceImplemantation(LessonStudentRepository lessonStudentRepository, LessonStudentMapper lessonStudentMapper) {
        this.lessonStudentRepository = lessonStudentRepository;
        this.lessonStudentMapper = lessonStudentMapper;
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

    @Override
    public void removeLessonStudent(Long lessonId, Long studentId) {
        lessonStudentRepository.findAllByLessonIdAndStudentId(lessonId, studentId)
                .forEach(lessonStudent -> {
                    LessonStudent lessonStudentInDb = lessonStudentRepository.findById(lessonStudent.getId()).orElseThrow(() -> new NoSuchElementException("LessonStudent could not be found"));
                    lessonStudentInDb.setDeleted(true);
                    lessonStudentRepository.save(lessonStudentInDb);
                });
    }

    @Override
    public List<LessonStudentDto> getLessonStudentsByInstructorId(Long instructorId) {
        return lessonStudentRepository.findByLessonIsDeletedFalseAndStudentIsDeletedFalseAndLessonInstructorId(instructorId)
                //.stream().map((element) -> modelMapper.map(element, LessonStudentDto.class)).toList();
                .stream().map(lessonStudentMapper::convertToDto).toList();
    }


}
