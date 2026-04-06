package com.pm.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter // Avoid @Data in entity fields
public class Patient {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Generate strategy
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Email // validation to make sure it is eMail form
    @Column(unique = true)  // validation to make sure there is no duplicate email in the database when saving it
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate registeredDate;

}
