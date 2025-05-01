package com.cankus.mapper;

import com.cankus.dto.CourseStudentDto;
import com.cankus.entity.CourseStudent;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseStudentMapper {

    private final ModelMapper modelMapper;

    public CourseStudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CourseStudent convertToEntity(CourseStudentDto courseStudentDto) {
        return modelMapper.map(courseStudentDto, CourseStudent.class);
    }

    public CourseStudentDto convertToDto(CourseStudent courseStudent) {
        return modelMapper.map(courseStudent, CourseStudentDto.class);
    }


}
