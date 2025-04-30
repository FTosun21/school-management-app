package com.cankus.repository;

import com.cankus.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    List<CourseStudent> findByStudentIdAndCourseIsDeletedFalseAndStudentIsDeletedFalse(Long studentId);

}
