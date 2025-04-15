package com.cankus.dto;

import com.cankus.entity.User;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class CourseDto {

    private Long id;
    private String name;
    private String description;
    private User courseManager;
    private LocalDate startDate;
    private LocalDate endDate;

    public CourseDto() {
    }
    public CourseDto(Long id, String name, String description, User courseManager, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.courseManager = courseManager;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
