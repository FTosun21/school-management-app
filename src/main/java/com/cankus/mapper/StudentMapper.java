package com.cankus.mapper;

import com.cankus.dto.StudentDto;
import com.cankus.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Student convertToEntity(StudentDto studentDto){
        return modelMapper.map(studentDto,Student.class);
    }
    public StudentDto convertToDto(Student student){
        return modelMapper.map(student,StudentDto.class);
    }
}
