package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_student")
@SQLRestriction("is_deleted is false")
public class LessonStudent extends BaseEntity {

    @ManyToOne
    private Lesson lesson;

    @ManyToOne
    private Student student;

    public LessonStudent() {
    }

    public LessonStudent(Long id, Boolean isDeleted, LocalDateTime insertDateTime,
                         Long insertUserId, LocalDateTime lastUpdateDateTime,
                         Long lastUpdateUserId, Lesson lesson, Student student) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.lesson = lesson;
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
