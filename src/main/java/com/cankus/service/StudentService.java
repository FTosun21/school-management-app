package com.cankus.service;

import com.cankus.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List <StudentDto> findAll();

    void save(StudentDto studentDto);

    StudentDto findById(Long id);

    void update(StudentDto studentDto);
}
