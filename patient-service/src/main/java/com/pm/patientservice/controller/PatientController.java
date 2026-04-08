package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients") // http://localhost:4000/patients
@RequiredArgsConstructor
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {
    private final PatientService patientService;

    // Use RequiredArgsConstructor to avoid constructor
//    public PatientController(PatientService patientService) {
//        this.patientService = patientService;
//    }

    //    @GetMapping
//    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
//        List<PatientResponseDTO> patients = patientService.getPatients();
//        return ResponseEntity.ok().body(patients);
//    }
    @GetMapping
    @Operation(summary = "Get Patients")
    //@ResponseStatus(HttpStatus.OK) remove the HttpStatus.OK since the 200 is default
    public List<PatientResponseDTO> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Patient")
    public PatientResponseDTO createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        return patientService.createPatient(patientRequestDTO);
    }

    @PutMapping("/{patientId}")
    @Operation(summary = "Update Patient")
    public PatientResponseDTO updatePatient(@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO,
                                            @PathVariable UUID patientId) {
        return patientService.updatePatient(patientRequestDTO, patientId);
    }

    @DeleteMapping("/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Patient")
    public void deletePatient(@PathVariable UUID patientId) {
        patientService.deletePatient(patientId);
    }
}
