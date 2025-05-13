package com.cankus.service.implementation;

import com.cankus.dto.AssessmentDto;
import com.cankus.dto.LessonStudentDto;
import com.cankus.mapper.AssessmentMapper;
import com.cankus.repository.AssessmentRepository;
import com.cankus.service.AssessmentService;
import com.cankus.service.LessonStudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssessmentServiceImplementation implements AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final AssessmentMapper assessmentMapper;
    private final LessonStudentService lessonStudentService;


    public AssessmentServiceImplementation(AssessmentRepository assessmentRepository, AssessmentMapper assessmentMapper, LessonStudentService lessonStudentService ) {
        this.assessmentRepository = assessmentRepository;
        this.assessmentMapper = assessmentMapper;
        this.lessonStudentService = lessonStudentService;

    }

    @Override
    public List<AssessmentDto> findAllByLessonStudentId(Long lessonStudentId) {
        return assessmentRepository.findAllByLessonStudentId(lessonStudentId)
                .stream()
                .map(assessmentMapper::convertToDto)
                .toList();
    }

    @Override
    public AssessmentDto getAssessmentDtoWithLessonStudent(Long lessonStudentId) {
        AssessmentDto assessmentDto = new AssessmentDto();
        LessonStudentDto currentLessonStudent = lessonStudentService.findById(lessonStudentId);
        assessmentDto.setLessonStudent(currentLessonStudent);
        return assessmentDto;
    }

    @Override
    public void save(AssessmentDto assessmentDto, Long lessonStudentId) {
        LessonStudentDto lessonStudentDto = lessonStudentService.findById(lessonStudentId);
        assessmentDto.setLessonStudent(lessonStudentDto);
        assessmentDto.setGradeDate(LocalDateTime.now());
        assessmentRepository.save(assessmentMapper.convertToEntity(assessmentDto));
    }
}
