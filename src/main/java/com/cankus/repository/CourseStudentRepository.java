package com.cankus.repository;

import com.cankus.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    // *us8-3     -> CSSImpl
    List<CourseStudent> findAllByCourseId(Long id);

    List<CourseStudent> findAllByCourseIdAndIsEnrolled(Long courseId, boolean enrolled);

    boolean existsByCourseIdAndStudentId(Long courseId,Long studentId);

    // 1. SEÇENEK (Derived Query - Tercih edilen)
    List<CourseStudent> findByStudentIdAndCourseIsDeletedFalseAndStudentIsDeletedFalse(Long studentId);

    // 2. SEÇENEK (JPQL - Sadece özel durumlarda)
//    @Query("SELECT cs FROM CourseStudent cs " +
//            "WHERE cs.student.id = :studentId " +
//            "AND cs.course.isDeleted = false " +
//            "AND cs.student.isDeleted = false")
//    List<CourseStudent> findByStudentId(@Param("studentId") Long studentId);


}
