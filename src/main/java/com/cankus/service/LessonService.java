package com.cankus.service;

import com.cankus.dto.LessonDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> findAll();

    void save(LessonDto lessonDto);

    LessonDto findById(Long id);

    void update(LessonDto lessonDto);

    void delete(Long id);

    boolean hasAssignedLessons(Long instructorId);

}
