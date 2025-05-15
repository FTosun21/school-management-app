package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "assessments")
@SQLRestriction("is_deleted is false")
public class Assessment extends BaseEntity {

    private String instructorImpressionOfStudent;
    private Integer grade;
    private LocalDateTime gradeDate;
    @ManyToOne
    private LessonStudent lessonStudent;

    public Assessment() {
    }

    public Assessment(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId,
                      LocalDateTime lastUpdateDateTime, Long lastUpdateUserId,
                      String instructorImpressionOfStudent, Integer grade, LocalDateTime gradeDate,
                      LessonStudent lessonStudent) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.instructorImpressionOfStudent = instructorImpressionOfStudent;
        this.grade = grade;
        this.gradeDate = gradeDate;
        this.lessonStudent = lessonStudent;
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

    public LessonStudent getLessonStudent() {
        return lessonStudent;
    }

    public void setLessonStudent(LessonStudent lessonStudent) {
        this.lessonStudent = lessonStudent;
    }
}
