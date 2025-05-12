package com.cankus.mapper;

import com.cankus.dto.AssessmentDto;
import com.cankus.entity.Assessment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssessmentMapper {

    private final ModelMapper modelMapper;

    public AssessmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Entity
    public Assessment convertToEntity(AssessmentDto assessmentDto) {
        return assessmentDto == null ? null : modelMapper.map(assessmentDto, Assessment.class);
    }

    // Entity -> DTO
    public AssessmentDto convertToDto(Assessment assessment) {
        return assessment == null ? null : modelMapper.map(assessment, AssessmentDto.class);
    }
}
