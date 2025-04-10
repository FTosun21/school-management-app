package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import com.cankus.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address extends BaseEntity {


    // Telefon numarası
    @Column(nullable = false)
    private String phoneNumber;

    // Ek adres bilgisi (opsiyonel)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State addressInfo;

    // Kullanıcının yaşadığı eyalet (enum yerine manuel string)
    @Column(nullable = false)
    private String state;
}
