package com.taoufiq.project.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.taoufiq.project.Models.*;
import com.taoufiq.project.Repositories.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
    public List<Patient> searchPatients(String searchString) {
    return patientRepository.findAll().stream()
            .filter(p -> p.getFirstName().toLowerCase().contains(searchString.toLowerCase()) ||
                         p.getLastName().toLowerCase().contains(searchString.toLowerCase()) ||
                         p.getEmail().toLowerCase().contains(searchString.toLowerCase()))
            .collect(Collectors.toList());
}

}