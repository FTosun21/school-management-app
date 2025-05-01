package com.cankus.dto;

import com.cankus.entity.Course;
import com.cankus.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LessonDto {

    private Long id;
    @NotBlank(message = "First Name is a required field.")
    @Size(max = 40, min = 2, message = "First Name must be between 2 and 15 characters long.")
    @Pattern(regexp = "^[A-Za-z]\\w*(?:\\s[A-Za-z]\\w*)*$", message = "First Name must starts with Uppercase character.")
    private String name;
    @NotBlank(message = "First Name is a required field")
    @Size(max = 100, min = 5, message = "First Name must be between 2 and 15 characters long.")
    private String description;
    @NotNull(message = "Please select a Course.")
    private CourseDto course;
    @NotNull(message = "Please select an Instructor.")
    private UserDto instructor;

    public LessonDto() {
    }

    public LessonDto(Long id, String name, CourseDto course, String description, UserDto instructor) {
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

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public UserDto getInstructor() {
        return instructor;
    }

    public void setInstructor(UserDto instructor) {
        this.instructor = instructor;
    }
}
