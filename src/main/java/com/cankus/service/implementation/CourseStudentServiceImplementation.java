package com.cankus.service.implementation;

import com.cankus.entity.Course;
import com.cankus.entity.CourseStudent;
import com.cankus.mapper.CourseStudentMapper;
import com.cankus.repository.CourseRepository;
import com.cankus.repository.CourseStudentRepository;
import com.cankus.repository.StudentRepository;
import com.cankus.service.CourseStudentService;
import org.springframework.stereotype.Service;
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

    //*us8-2 implement service method ->CSRepository
    @Override
    public void removeCourseStudentByCourse(Long courseId) {
        //*us8-4 --> CSImpl
        courseStudentRepository.findAllByCourseId(courseId)
                .forEach(courseStudent -> {
                    CourseStudent courseStudentInDB=courseStudentRepository.findById(courseStudent.getId())
                            .orElseThrow(()->new NoSuchElementException("CourseStudent could not be found."));
                    courseStudentInDB.setDeleted(true);
                    courseStudentRepository.save(courseStudentInDB);
                });
    }
}
