package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import com.cankus.enums.State;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@SQLRestriction("is_deleted is false")
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {


    // Telefon numarası
    @Column(nullable = false)
    private String phoneNumber;

    // Ek adres bilgisi (opsiyonel)
    @Column(nullable = false)
    private String addressInfo;

    // Kullanıcının yaşadığı eyalet (enum yerine manuel string)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    public Address(){}

    public Address(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String phoneNumber, String addressInfo, State state) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.phoneNumber = phoneNumber;
        this.addressInfo = addressInfo;
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
