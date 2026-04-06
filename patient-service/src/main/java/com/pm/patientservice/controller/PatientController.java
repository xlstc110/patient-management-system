package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients") // http://localhost:4000/patients
@RequiredArgsConstructor
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
    //@ResponseStatus(HttpStatus.OK) remove the HttpStatus.OK since the 200 is default
    public List<PatientResponseDTO> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponseDTO createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        return patientService.createPatient(patientRequestDTO);
    }
}
