package com.taoufiq.project.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taoufiq.project.DTOs.PatientDTO;
import com.taoufiq.project.Mappers.PatientMapper;
import com.taoufiq.project.Models.Patient;
import com.taoufiq.project.Services.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/adsweb/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper patientMapper = PatientMapper.INSTANCE;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<PatientDTO> getAll() {
        return patientService.findAll().stream()
                .sorted((p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName()))
                .map(patientMapper::patientToPatientDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PatientDTO> create(@RequestBody @Valid Patient patient) {
        Patient savedPatient = patientService.save(patient);
        return ResponseEntity.ok(patientMapper.patientToPatientDTO(savedPatient));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<PatientDTO> getById(@PathVariable Long id) {
        Patient found = patientService.findById(id);
        return found != null ? ResponseEntity.ok(patientMapper.patientToPatientDTO(found)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/patient/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody @Valid Patient patient) {
        patient.setId(id);
        Patient updatedPatient = patientService.save(patient);
        return ResponseEntity.ok(patientMapper.patientToPatientDTO(updatedPatient));
    }

    @DeleteMapping("/patient/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
