package com.cankus.dto;

public class LessonStudentDto {

    private Long id;

    private StudentDto student;

    private LessonDto lesson;

    public LessonStudentDto() {
    }

    public LessonStudentDto(Long id, StudentDto student, LessonDto lesson) {
        this.id = id;
        this.student = student;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public LessonDto getLesson() {
        return lesson;
    }

    public void setLesson(LessonDto lesson) {
        this.lesson = lesson;
    }
}
