package com.cankus.mapper;

import com.cankus.dto.CourseDto;
import com.cankus.entity.Course;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    private final ModelMapper modelMapper;

    public CourseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Entity
    public Course convertToEntity(CourseDto courseDto){
        return modelMapper.map(courseDto,Course.class);
    }

    // Entity -> DTO
    public CourseDto convertToDto(Course course){
        return modelMapper.map(course, CourseDto.class);
    }

}
