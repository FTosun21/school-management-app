package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import com.cankus.enums.Gender;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@SQLRestriction("is_deleted is false")
public class Student extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Student() {
    }

    public Student(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId,
                   LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String firstName,
                   String lastName, String email, Gender gender, Address address) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
