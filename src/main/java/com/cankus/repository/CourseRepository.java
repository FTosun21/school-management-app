package com.cankus.repository;

import com.cankus.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    boolean existsByCourseManagerId(Long managerId);
    // Get all non-deleted courses
    List<Course> findAllByIsDeletedFalse();

}
