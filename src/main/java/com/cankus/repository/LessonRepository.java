package com.cankus.repository;

import com.cankus.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    boolean existsByInstructorId(Long instructorId);
    // *us8-8 -->LSImpl
    List<Lesson> findAllByCourseId(Long courseId);

}
