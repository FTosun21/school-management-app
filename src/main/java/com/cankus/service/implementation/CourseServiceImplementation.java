package com.cankus.service.implementation;

import com.cankus.dto.CourseDto;
import com.cankus.entity.Course;
import com.cankus.mapper.CourseMapper;
import com.cankus.repository.CourseRepository;
import com.cankus.service.CourseService;
import com.cankus.service.CourseStudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseStudentService courseStudentService;

    public CourseServiceImplementation(CourseRepository courseRepository, CourseMapper courseMapper, CourseStudentService courseStudentService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseStudentService = courseStudentService;
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

    }

    @Override
    public boolean hasAssignedCourses(Long managerId) {
        return courseRepository.existsByCourseManagerId(managerId);
    }
}
