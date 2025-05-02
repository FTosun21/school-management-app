package com.cankus.converter;


import com.cankus.dto.CourseDto;
import com.cankus.service.CourseService;
import jakarta.annotation.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoConverter implements Converter <String,CourseDto> {

    private final CourseService courseService;

    public CourseDtoConverter(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CourseDto convert(@Nullable String source) {
        if (source == null || source.isBlank()) {
            return null;
        }
        return courseService.findById(Long.parseLong(source));
    }

}
