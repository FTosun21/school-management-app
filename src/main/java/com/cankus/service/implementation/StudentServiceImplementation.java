package com.cankus.service.implementation;

import com.cankus.dto.StudentDto;
import com.cankus.entity.Student;
import com.cankus.mapper.StudentMapper;
import com.cankus.repository.StudentRepository;
import com.cankus.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public void save(StudentDto studentDto) {
        Student student = studentMapper.convertToEntity(studentDto);
        studentRepository.save(student);

    }

    @Override
    public StudentDto findById(Long id) {
        Student studentInDB = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student could not be found."));
        return studentMapper.convertToDto(studentInDB);
    }
}
