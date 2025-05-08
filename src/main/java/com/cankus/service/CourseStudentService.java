package com.cankus.service;

public interface CourseStudentService {

    void assingNewCourseToAllStudent(Long courseId);
    //us8-1 --> CSSImpl
    void removeCourseStudentByCourse(Long courseId);

    void assignAllCourseToNewStudent(Long studentId);
}
