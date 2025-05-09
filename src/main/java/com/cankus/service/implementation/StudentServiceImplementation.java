package com.cankus.service.implementation;

import com.cankus.dto.StudentDto;
import com.cankus.entity.Student;
import com.cankus.mapper.StudentMapper;
import com.cankus.repository.StudentRepository;
import com.cankus.service.AddressService;
import com.cankus.service.CourseStudentService;
import com.cankus.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AddressService addressService;
    private final CourseStudentService courseStudentService;

    public StudentServiceImplementation(StudentRepository studentRepository , StudentMapper studentMapper, AddressService addressService , CourseStudentService courseStudentService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.addressService = addressService;
        this.courseStudentService = courseStudentService;
    }

    public List<StudentDto> findAll(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(StudentDto studentDto) {
        // 1) Convert DTO to Entity and save student
        Student student = studentRepository.save(studentMapper.convertToEntity(studentDto));
        courseStudentService.assignAllCourseToNewStudent(student.getId());
    }

    @Override
    public StudentDto findById(Long id) {
        Student studentInDB = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student could not be found."));
        return studentMapper.convertToDto(studentInDB);
    }

    @Override
    public void update(StudentDto studentDto) {
        Student student = studentMapper.convertToEntity(studentDto);
        studentRepository.save(student);
    }
    @Override
    public void delete(Long id) {
        Student studentInDB = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student could not be found."));
        addressService.delete(id);
        studentInDB.setEmail(studentInDB.getEmail() + "-" + studentInDB.getId());
        studentInDB.setDeleted(true);
        studentRepository.save(studentInDB);
    }
}
