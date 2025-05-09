package com.cankus.service;

import com.cankus.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAll();

    void save(CourseDto courseDto);

    CourseDto findById(Long id);

    void update(CourseDto courseDto);

    void delete(Long id);

    boolean hasAssignedCourses(Long managerId);

    // *us8-10   -->CSImpl
    boolean checkAssignedLesson(Long courseId);
}
