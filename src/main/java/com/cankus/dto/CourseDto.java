package com.cankus.dto;

import com.cankus.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CourseDto {

    private Long id;
    @NotBlank(message = "Course name is a required field.")
    @Size(max = 40,min = 2,message = "Course Name must be between 2 and 40 characters long.")
    @Pattern(regexp = "^[A-Za-z]\\w*(?:\\s[A-Za-z]\\w*)*$", message = "Just use alphabetic characters with spaces.")
    private String name;
    @NotBlank(message = "Course Description is a required field.")
    @Size(max = 100,min = 5,message = "Course Description must be between 5 and 100 characters long.")
    private String description;
    @NotNull(message = "Please select a course manager!")
    private UserDto courseManager;
    @NotNull(message = "Please select a start date!")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;
    @NotNull(message = "Please select an end date!")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    public CourseDto() {
    }
    public CourseDto(Long id, String name, String description, UserDto courseManager, LocalDate startDate, LocalDate endDate) {
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

    public UserDto getCourseManager() {
        return courseManager;
    }

    public void setCourseManager(UserDto courseManager) {
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
