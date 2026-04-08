package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientRequestDTO (
        @NotBlank
        // Add the validation in DTO before it sends to a database
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Need a valid Email")
        String email,
        String address,
        String dateOfBirth,
        @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registration date is required")
        String registeredDate
){}
