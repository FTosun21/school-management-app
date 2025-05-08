package com.cankus.service;

import com.cankus.dto.CourseStudentDto;

import java.util.List;

public interface CourseStudentService {

    void assingNewCourseToAllStudent(Long courseId);
    //us8-1 --> CSSImpl
    void removeCourseStudentByCourse(Long courseId);

}
