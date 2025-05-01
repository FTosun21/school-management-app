package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;


@Entity
@Table(name = "course_student")
@SQLRestriction("is_deleted is false")
public class CourseStudent extends BaseEntity {

    private boolean isEnrolled;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    public CourseStudent() {
    }

    public CourseStudent(boolean isEnrolled, Course course, Student student) {
        this.isEnrolled = isEnrolled;
        this.course = course;
        this.student = student;
    }

    public CourseStudent(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, boolean isEnrolled, Course course, Student student) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.isEnrolled = isEnrolled;
        this.course = course;
        this.student = student;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
