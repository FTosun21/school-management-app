package com.cankus.entity;

import com.cankus.entity.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private String description;

    public Role() {
    }

    public Role(Long id, Boolean isDeleted, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String description) {
        super(id, isDeleted, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
