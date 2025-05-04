package com.cankus.repository;

import com.cankus.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    // Özel sorgu metodunu ekleyin
    Optional<Student> findByIdAndIsDeletedFalse(Long id);

    // Alternatif olarak bu şekilde de yazabilirsiniz:
//   @Query("SELECT s FROM Student s WHERE s.id = :id AND s.isDeleted = false")
//   Optional<Student> findActiveById(@Param("id") Long id);
}
