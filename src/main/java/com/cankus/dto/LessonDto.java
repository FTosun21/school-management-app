package com.cankus.dto;

import com.cankus.entity.Course;
import com.cankus.entity.User;

public class LessonDto {

    private Long id;
    private String name;
    private String description;
    private Course course;
    private User instructor;

    public LessonDto() {
    }

    public LessonDto(Long id, String name, Course course, String description, User instructor) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.description = description;
        this.instructor = instructor;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }
}
