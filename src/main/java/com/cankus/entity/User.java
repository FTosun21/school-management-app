package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import com.cankus.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY) // cascading nedir fetchtype nedir?
    @JoinColumn(name = "role_id")
    private Role role;

}
// cascading nedir fetchtype nedir?  validations annotation  Dto annotations lar  mapper ve converter