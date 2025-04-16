package com.cankus.service;

import com.cankus.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> findAll();
}
