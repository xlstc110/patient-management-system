package com.pm.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PatientResponseDTO (
        String id,
        @NotBlank
        @Size(max = 100)
        String name,
        String email,
        String address,
        String dateOfBirth
){}
