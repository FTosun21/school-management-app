package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import com.cankus.enums.Gender;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@SQLRestriction("is_deleted is false")
public class User extends BaseEntity {

    // Kullanıcının adı
    @Column(nullable = false)
    private String firstName;

    // Kullanıcının soyadı
    @Column(nullable = false)
    private String lastName;

    // Kullanıcı adı, benzersiz olmalı
    //@Column( unique = true)
    private String userName;

    // Şifre
    @Column(nullable = false)
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    private Boolean enabled;

    // Cinsiyet enum olarak gelir, veritabanına string yazılır
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    // Kullanıcının adresi
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id")
    private Address address;


    // Kullanıcı rolü ile ilişki (bir kullanıcı bir role sahiptir)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }


    public User(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String firstName, String lastName, String userName, String password, String confirmPassword, Boolean enabled, Gender gender, Address address, Role role) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enabled = enabled;
        this.gender = gender;
        this.address = address;
        this.role = role;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
