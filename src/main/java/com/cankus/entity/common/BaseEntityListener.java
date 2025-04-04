package com.cankus.entity.common;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

//    @PrePersist
//    public void onPrePersist(BaseEntity baseEntity) {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LocalDateTime now = LocalDateTime.now();
//        baseEntity.setInsertDateTime(now);
//        baseEntity.setLastUpdateDateTime(now);
//
//        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
//            try {
//                UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//                baseEntity.setInsertUserId(userPrincipal.getId());
//                baseEntity.setLastUpdateUserId(userPrincipal.getId());
//            } catch (Exception e) {
//                baseEntity.setInsertUserId(999L);
//                baseEntity.setLastUpdateUserId(999L);
//            }
//        }
//    }
//
//    @PreUpdate
//    public void onPreUpdate(BaseEntity baseEntity) {
//
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
//
//        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
//            try {
//                UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//                baseEntity.setLastUpdateUserId(principal.getId());
//            } catch (Exception e) {
//                baseEntity.setLastUpdateUserId(999L);
//            }
//        }
//    }


    @PrePersist
    public void onPrePersist(BaseEntity baseEntity) {

        LocalDateTime now = LocalDateTime.now();
        baseEntity.setInsertDateTime(now);
        baseEntity.setLastUpdateDateTime(now);

        baseEntity.setInsertUserId(1L);
        baseEntity.setLastUpdateUserId(1L);
    }


    @PreUpdate
    public void onPreUpdate(BaseEntity baseEntity) {

        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        baseEntity.setLastUpdateUserId(1L);
        baseEntity.setLastUpdateUserId(1L);
    }


}


