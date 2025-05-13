package com.cankus.service;

import com.cankus.dto.AssessmentDto;

import java.util.List;

public interface AssessmentService {

    List<AssessmentDto> findAllByLessonStudentId(Long lessonStudentId);

    AssessmentDto getAssessmentDtoWithLessonStudent(Long lessonStudentId);

    void save(AssessmentDto assessmentDto,Long lessonStudentId);

}
