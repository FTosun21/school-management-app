package com.cankus.repository;

import com.cankus.entity.LessonStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonStudentRepository extends JpaRepository<LessonStudent, Long> {

    // Check if assignment between this lesson and student already exists
    // SELECT COUNT(*) > 0 FROM lesson_student WHERE lesson_id = :lessonId AND student_id = :studentId AND is_deleted = false;
    boolean existsByLessonIdAndStudentId(Long lessonId, Long studentId);

    List<LessonStudent> findAllByLessonIdAndStudentId(Long lessonID, Long studentId);

    //Us20-1 way
   // List<LessonStudent> findLessonStudentByLessonIsDeletedAndStudentIsDeletedAndLessonInstructorId(Boolean lessonIsDeleted, Boolean studentIsDeleted, Long lessonInstructorId);
    //Us20-2 way
    List<LessonStudent> findByLessonIsDeletedFalseAndStudentIsDeletedFalseAndLessonInstructorId(Long instructorId);


}



