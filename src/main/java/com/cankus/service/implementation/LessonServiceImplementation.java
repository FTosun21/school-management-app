package com.cankus.service.implementation;

import com.cankus.dto.LessonDto;
import com.cankus.entity.Lesson;
import com.cankus.mapper.LessonMapper;
import com.cankus.repository.LessonRepository;
import com.cankus.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public void save(LessonDto lessonDto) {
        Lesson lesson=lessonMapper.convertToEntity(lessonDto);
        lessonRepository.save(lesson);
    }

    @Override
    public LessonDto findById(Long id) {
        Lesson lessonInDB = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lesson could not be found."));
        return lessonMapper.convertToDto(lessonInDB);
    }

    @Override
    public void update(LessonDto lessonDto) {
        Lesson lesson = lessonMapper.convertToEntity(lessonDto);
        lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {
        Lesson lessonInDB = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lesson could not be found."));
        lessonInDB.setDeleted(true);
        lessonRepository.save(lessonInDB);
    }

    @Override
    public boolean hasAssignedLessons(Long instructorId) {
        return lessonRepository.existsByInstructorId(instructorId);
    }
}
