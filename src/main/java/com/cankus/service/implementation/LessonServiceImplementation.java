package com.cankus.service.implementation;

import com.cankus.dto.LessonDto;
import com.cankus.mapper.LessonMapper;
import com.cankus.repository.LessonRepository;
import com.cankus.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImplementation implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public LessonServiceImplementation(LessonRepository lessonRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
    }


    @Override
    public List<LessonDto> findAll() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
