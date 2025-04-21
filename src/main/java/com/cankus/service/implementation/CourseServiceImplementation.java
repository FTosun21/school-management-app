package com.cankus.service.implementation;

import com.cankus.dto.CourseDto;
import com.cankus.entity.Course;
import com.cankus.mapper.CourseMapper;
import com.cankus.repository.CourseRepository;
import com.cankus.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImplementation(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void save(CourseDto courseDto) {
        Course course=courseMapper.convertToEntity(courseDto);
        courseRepository.save(course);
    }
}
