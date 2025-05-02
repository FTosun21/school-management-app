package com.cankus.service.implementation;

import com.cankus.dto.LessonDto;
import com.cankus.entity.*;
import com.cankus.mapper.LessonMapper;
import com.cankus.repository.CourseRepository;
import com.cankus.repository.CourseStudentRepository;
import com.cankus.repository.LessonRepository;
import com.cankus.repository.LessonStudentRepository;
import com.cankus.service.LessonService;
import com.cankus.service.LessonStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LessonServiceImplementation implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final CourseStudentRepository courseStudentRepository;
    private final CourseRepository courseRepository;
    private final LessonStudentService lessonStudentService;

    public LessonServiceImplementation(LessonRepository lessonRepository, LessonMapper lessonMapper, CourseStudentRepository courseStudentRepository, CourseRepository courseRepository, LessonStudentService lessonStudentService) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.courseStudentRepository = courseStudentRepository;
        this.courseRepository = courseRepository;
        this.lessonStudentService = lessonStudentService;
    }


    @Override
    public List<LessonDto> findAll() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional  // All operations inside this method are done in one transaction
    public void save(LessonDto lessonDto) {
        // 1) Convert DTO to Entity and save Lesson
        Lesson lesson = lessonRepository.save(lessonMapper.convertToEntity(lessonDto));

        // 2) Get the course of this lesson and validate existence
        Course course = courseRepository.findById(lesson.getCourse().getId())
                .orElseThrow(() -> new NoSuchElementException("Course could not be found"));

        // 3) Get all enrolled (active) students for this course
        List<CourseStudent> enrolledStudents = courseStudentRepository
                .findAllByCourseIdAndIsEnrolled(course.getId(), true);

        // 4. Assign the lesson to these students
        lessonStudentService.assignLessonToStudents(lesson, enrolledStudents);
    }

    @Override
    public LessonDto findById(Long id) {
        Lesson lessonInDB = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lesson could not be found."));
        return lessonMapper.convertToDto(lessonInDB);
    }

    @Override
    public void update(LessonDto lessonDto) {
        Lesson lesson = lessonMapper.convertToEntity(lessonDto);
        lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {
        Lesson lessonInDB = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lesson could not be found."));
        lessonInDB.setDeleted(true);
        lessonRepository.save(lessonInDB);
    }

    @Override
    public boolean hasAssignedLessons(Long instructorId) {
        return lessonRepository.existsByInstructorId(instructorId);
    }

    // *us8-7 -->L_repository
    @Override
    public List<LessonDto> getAllLessonsByCourseId(Long courseId) {
        //*us8-9  --> C_Service
        return lessonRepository.findAllByCourseId(courseId)
                .stream()
                .map(lessonMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
