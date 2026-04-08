package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    // Derived Query Method (Query Method)
    boolean existsByEmail(String email);

    // Annotated Query (@Query)
//    @Query("SELECT p FROM Patient p WHERE p.email = :email")
//    Optional<Patient> findByEmail(@Param("email") String email);

    // Check if the email exists and the user ID is not equals to the user
    //boolean existsByEmailAndIdNot(String email, UUID patientId);
}
