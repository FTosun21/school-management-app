package com.cankus.mapper;

import com.cankus.dto.LessonDto;
import com.cankus.entity.Lesson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    private final ModelMapper modelMapper;

    public LessonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Entity
    public Lesson convertToEntity(LessonDto lessonDto){
        return modelMapper.map(lessonDto,Lesson.class);
    }

    // Entity -> DTO
    public LessonDto convertToDto( Lesson lesson){
        return modelMapper.map(lesson, LessonDto.class);
    }

}
