package com.cankus.dto;

public class CourseStudentDto {

    private Long id;

    private boolean isEnrolled;

    private CourseDto course;

    private StudentDto student;

    public CourseStudentDto() {
    }
    public CourseStudentDto(Long id, boolean isEnrolled, CourseDto course, StudentDto student) {
        this.id = id;
        this.isEnrolled = isEnrolled;
        this.course = course;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }
}
