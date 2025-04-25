package com.cankus.service;

import com.cankus.dto.LessonDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> findAll();

    void save(LessonDto lessonDto);
}
