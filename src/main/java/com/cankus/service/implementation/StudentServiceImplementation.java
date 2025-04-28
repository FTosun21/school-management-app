package com.cankus.service.implementation;

import com.cankus.dto.StudentDto;
import com.cankus.mapper.StudentMapper;
import com.cankus.repository.StudentRepository;
import com.cankus.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImplementation(StudentRepository studentRepository , StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDto> findAll(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::convertToDto).collect(Collectors.toList());
    }
}
