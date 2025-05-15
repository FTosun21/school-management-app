package com.cankus.dto;

import java.time.LocalDateTime;

public class AssessmentDto {
    private String instructorImpressionOfStudent;
    private Integer grade;
    private LocalDateTime gradeDate;
    private LessonStudentDto lessonStudent;

    public AssessmentDto() {
    }

    public String getInstructorImpressionOfStudent() {
        return instructorImpressionOfStudent;
    }

    public void setInstructorImpressionOfStudent(String instructorImpressionOfStudent) {
        this.instructorImpressionOfStudent = instructorImpressionOfStudent;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDateTime getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(LocalDateTime gradeDate) {
        this.gradeDate = gradeDate;
    }

    public LessonStudentDto getLessonStudent() {
        return lessonStudent;
    }

    public void setLessonStudent(LessonStudentDto lessonStudent) {
        this.lessonStudent = lessonStudent;
    }
}
