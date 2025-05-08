package com.cankus.service.implementation;

import com.cankus.dto.CourseStudentDto;
import com.cankus.entity.*;
import com.cankus.mapper.CourseStudentMapper;
import com.cankus.repository.*;
import com.cankus.service.CourseStudentService;
import com.cankus.service.LessonStudentService;
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
    private final CourseStudentMapper courseStudentMapper;
    private final LessonRepository lessonRepository;
    private final LessonStudentRepository lessonStudentRepository;
    private final LessonStudentService lessonStudentService;

    public CourseStudentServiceImplementation(CourseStudentRepository courseStudentRepository, CourseRepository courseRepository, StudentRepository studentRepository, CourseStudentMapper courseStudentMapper, LessonRepository lessonRepository, LessonStudentRepository lessonStudentRepository, LessonStudentService lessonStudentService) {
        this.courseStudentRepository = courseStudentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseStudentMapper = courseStudentMapper;
        this.lessonRepository = lessonRepository;
        this.lessonStudentRepository = lessonStudentRepository;
        this.lessonStudentService = lessonStudentService;
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

    @Override
    public List<CourseStudentDto> findAllByStudentId(Long id) {
        return courseStudentRepository.findByStudentIdAndCourseIsDeletedFalseAndStudentIsDeletedFalse(id)
                .stream()
                .map(courseStudentMapper::convertToDto).toList();
    }
    @Transactional
    @Override
    public void enroll(Long courseStudentId) {
        CourseStudent courseStudentInDB = courseStudentRepository.findById(courseStudentId)
                .orElseThrow(() -> new NoSuchElementException("Course student could not be found"));
        courseStudentInDB.setEnrolled(true);
        courseStudentRepository.save(courseStudentInDB);

        List<Lesson> lessonsOfThisCourse = lessonRepository.findAllByCourseId(courseStudentInDB.getCourse().getId());
        Student studentEnrolledThisCourse = studentRepository.findById(courseStudentInDB.getStudent().getId())
                .orElseThrow(() -> new NoSuchElementException("Student could not be found."));
        lessonsOfThisCourse.forEach(lesson -> {
            LessonStudent lessonStudent=new LessonStudent();
            lessonStudent.setLesson(lesson);
            lessonStudent.setStudent(studentEnrolledThisCourse);
            lessonStudentRepository.save(lessonStudent);
        });
    }
    @Transactional
    @Override
    public void drop(Long courseStudentId) {
        CourseStudent courseStudentInDB=courseStudentRepository.findById(courseStudentId)
                .orElseThrow(()-> new NoSuchElementException("Course student could not be found."));

        // 1) Set enrolled to false
        courseStudentInDB.setEnrolled(false);
        courseStudentRepository.save(courseStudentInDB);
        // 2) Delete LessonStudent records
        //We do not want to store lessonStudent information if the student dropped.
        List<Lesson> lessonsOfThisCourse = lessonRepository.findAllByCourseId(courseStudentInDB.getCourse().getId());

        lessonsOfThisCourse.forEach(lesson -> {
            lessonStudentService.removeLessonStudent(
                    lesson.getId(),
                    courseStudentInDB.getStudent().getId());
        });
    }
}
