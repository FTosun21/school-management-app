package com.cankus.repository;

import com.cankus.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsByCourseManagerId(Long managerId);
}
