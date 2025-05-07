package com.cankus.service;

import com.cankus.entity.CourseStudent;
import com.cankus.entity.Lesson;

import java.util.List;

public interface LessonStudentService {

    void assignLessonToStudents(Lesson lesson, List<CourseStudent> students);

    void removeLessonStudent(Long lessonId,Long studentId);
}
