package com.cankus.repository;

import com.cankus.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    boolean existsById(Long id);

}
