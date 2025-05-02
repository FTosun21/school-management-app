package com.cankus.repository;

import com.cankus.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    List<CourseStudent> findByStudentIdAndCourseIsDeletedFalseAndStudentIsDeletedFalse(Long studentId);
    // *us8-3     -> CSSImpl
    List<CourseStudent> findAllByCourseId(Long id);

    List<CourseStudent> findAllByCourseIdAndIsEnrolled(Long courseId, boolean enrolled);


}
