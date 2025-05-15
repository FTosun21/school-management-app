package com.cankus.repository;

import com.cankus.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment,Long> {

    List<Assessment> findAllByLessonStudentId(Long lessonStudentId);
}
