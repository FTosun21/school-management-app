package com.cankus.service.implementation;

import com.cankus.entity.Course;
import com.cankus.entity.CourseStudent;
import com.cankus.entity.Student;
import com.cankus.repository.CourseRepository;
import com.cankus.repository.CourseStudentRepository;
import com.cankus.repository.StudentRepository;
import com.cankus.service.CourseStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseStudentServiceImplementation implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseStudentServiceImplementation(CourseStudentRepository courseStudentRepository, CourseRepository courseRepository, StudentRepository studentRepository ) {
        this.courseStudentRepository = courseStudentRepository;
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

    @Transactional
    @Override
    public void assignAllCourseToNewStudent(Long studentId){
        // 1. Öğrenciyi soft delete durumuna göre ara
        Student student = studentRepository.findByIdAndIsDeletedFalse(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student could not be found by id: "+studentId));
        // 2) Get all active courses
        List<Course> activeCourses = courseRepository.findAllByIsDeletedFalse();

        // 3) Her course için CourseStudent ilişkisini oluştur (isEnrolled = false)
        List<CourseStudent> courseStudents = activeCourses.stream()
                .filter(course -> !courseStudentRepository.existsByCourseIdAndStudentId(
                        course.getId(), studentId)) // Çift atama önle
                .map(course -> {
                    CourseStudent cs = new CourseStudent();
                    cs.setCourse(course);
                    cs.setStudent(student);
                    cs.setEnrolled(false);
                    return cs;
                })
                .collect(Collectors.toList());
        // 4. Toplu kaydet
        courseStudentRepository.saveAll(courseStudents);
    }
}
