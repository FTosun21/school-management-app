package com.cankus.service.implementation;

import com.cankus.dto.CourseStudentDto;
import com.cankus.entity.Course;
import com.cankus.entity.CourseStudent;
import com.cankus.mapper.CourseStudentMapper;
import com.cankus.repository.CourseRepository;
import com.cankus.repository.CourseStudentRepository;
import com.cankus.repository.StudentRepository;
import com.cankus.service.CourseStudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseStudentServiceImplementation implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final CourseStudentMapper courseStudentMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseStudentServiceImplementation(CourseStudentRepository courseStudentRepository, CourseStudentMapper courseStudentMapper, CourseRepository courseRepository, StudentRepository studentRepository ) {
        this.courseStudentRepository = courseStudentRepository;
        this.courseStudentMapper = courseStudentMapper;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void assingNewCourseToAllStudent(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course could not be found."));
        studentRepository.findAll()
                .stream()
                .map(student -> new CourseStudent(false,course,student))
                .forEach(courseStudentRepository::save);
    }
}
