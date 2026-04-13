package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
//        return new PatientResponseDTO(
//                patient.getId().toString(),
//                patient.getName(),
//                patient.getEmail(),
//                patient.getAddress(),
//                patient.getDateOfBirth().toString()
//        );
        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public static Patient toPatientModel(PatientRequestDTO patientRequestDTO) {
        return Patient.builder()
                .name(patientRequestDTO.name())
                .email(patientRequestDTO.email())
                .address(patientRequestDTO.address())
                .dateOfBirth(LocalDate.parse(patientRequestDTO.dateOfBirth()))
                .registeredDate(LocalDate.parse(patientRequestDTO.registeredDate()))
                .build();
    }
}
