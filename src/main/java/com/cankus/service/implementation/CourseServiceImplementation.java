package com.cankus.service.implementation;

import com.cankus.dto.CourseDto;
import com.cankus.entity.Course;
import com.cankus.mapper.CourseMapper;
import com.cankus.repository.CourseRepository;
import com.cankus.service.CourseService;
import com.cankus.service.CourseStudentService;
import com.cankus.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseStudentService courseStudentService;
    private final LessonService lessonService;

    public CourseServiceImplementation(CourseRepository courseRepository, CourseMapper courseMapper, CourseStudentService courseStudentService, LessonService lessonService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseStudentService = courseStudentService;
        this.lessonService = lessonService;
    }

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void save(CourseDto courseDto) {
        Course course = courseMapper.convertToEntity(courseDto);
        Course savedCourse = courseRepository.save(course);
        courseStudentService.assingNewCourseToAllStudent(savedCourse.getId());
    }

    @Override
    public CourseDto findById(Long id) {
        Course courseInDB = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course could not be found."));
        return courseMapper.convertToDto(courseInDB);
    }

    @Override
    public void update(CourseDto courseDto) {
        Course course = courseMapper.convertToEntity(courseDto);
        courseRepository.save(course);
    }


    @Override
    public void delete(Long id) {
        Course courseInDB = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course could not be found."));

        courseInDB.setDeleted(true);
        courseRepository.save(courseInDB);
        // *us8-0 Remove all courseStudent -> CSSImpl
        // *us8-5  --> LService
        courseStudentService.removeCourseStudentByCourse(id);
    }

    @Override
    public boolean hasAssignedCourses(Long managerId) {
        return courseRepository.existsByCourseManagerId(managerId);
    }

    // *us8-11 --> C_Controller
    @Override
    public boolean checkAssignedLesson(Long courseId) {
        return !lessonService.getAllLessonsByCourseId(courseId).isEmpty();
    }
}
