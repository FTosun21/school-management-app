package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@SQLRestriction("is_deleted is false")
public class Course extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne
    private User courseManager;

    private LocalDate startDate;
    private LocalDate endDate;

    public Course() {
    }

    public Course(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String name, String description, User courseManager, LocalDate startDate, LocalDate endDate) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.name = name;
        this.description = description;
        this.courseManager = courseManager;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCourseManager() {
        return courseManager;
    }

    public void setCourseManager(User courseManager) {
        this.courseManager = courseManager;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
