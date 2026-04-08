package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(
                PatientMapper::toPatientResponseDTO
        ).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        patientEmailExists(patientRequestDTO.email());

        Patient newPatient = patientRepository.save(PatientMapper.toPatientModel(patientRequestDTO));

        return PatientMapper.toPatientResponseDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO, UUID patientId) {


        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new PatientNotFoundException("A patient with this ID: "
                + patientId + " is not found in database")
        );

        // If the user is updating the email, check the duplication against other emails
        if(!patient.getEmail().equals(patientRequestDTO.email())) {
            patientEmailExists(patientRequestDTO.email());
        }

        Patient UpdatedPatient = Patient.builder()
                .id(patientId)
                .name(patientRequestDTO.name())
                .email(patientRequestDTO.email())
                .address(patientRequestDTO.address())
                .dateOfBirth(LocalDate.parse(patientRequestDTO.dateOfBirth()))
                .registeredDate(patient.getRegisteredDate())
                .build();

        return PatientMapper.toPatientResponseDTO(UpdatedPatient);
    }

    public void patientEmailExists(String email) {
        if(patientRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistException("A patient with this email: "
                    + email + " already exist");
        }
    }

    public void deletePatient(UUID patientId) {
        patientRepository.deleteById(patientId);
    }
}
