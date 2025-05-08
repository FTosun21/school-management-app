package com.cankus.mapper;

import com.cankus.dto.LessonStudentDto;
import com.cankus.entity.LessonStudent;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonStudentMapper {

    private final ModelMapper modelMapper;

    public LessonStudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Entity
    public LessonStudent convertToEntity(LessonStudentDto lessonStudentDto) {
        return lessonStudentDto == null ? null : modelMapper.map(lessonStudentDto, LessonStudent.class);
    }

    // Entity -> DTO
    public LessonStudentDto convertToDto(LessonStudent lessonStudent) {
        return lessonStudent == null ? null : modelMapper.map(lessonStudent, LessonStudentDto.class);
    }

    // Liste Dönüşümü (Null-safe + Optimize)
    public List<LessonStudentDto> convertToDtoList(List<LessonStudent> lessonStudents) {
        return lessonStudents == null ? Collections.emptyList() :
                lessonStudents.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList());
    }
}
