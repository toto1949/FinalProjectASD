package com.taoufiq.project;


import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.taoufiq.project.Models.Patient;
import com.taoufiq.project.Repositories.PatientRepository;
import com.taoufiq.project.Services.PatientService;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository; // Mocking the PatientRepository

    @InjectMocks
    private PatientService patientService; // Injecting the mock repository into the service

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up a mock patient object for testing
        patient = new Patient(1L, "John", "Doe", "john.doe@example.com", null);
    }

    @Test
    void shouldFindAllPatients() {
        // Mocking the repository's findAll method
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));

        // Calling the service method
        var result = patientService.findAll();

        // Verifying the repository interaction and asserting the result
        verify(patientRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(patient.getFirstName(), result.get(0).getFirstName());
    }

    @Test
    void shouldSavePatient() {
        // Mocking the save method to return the patient
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Calling the service method
        Patient savedPatient = patientService.save(patient);

        // Verifying that the save method was called
        verify(patientRepository, times(1)).save(any(Patient.class));
        assertNotNull(savedPatient);
        assertEquals(patient.getEmail(), savedPatient.getEmail());
    }

    @Test
    void shouldFindPatientById() {
        // Mocking the findById method to return a patient
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Calling the service method
        Patient foundPatient = patientService.findById(1L);

        // Verifying the repository interaction and asserting the result
        verify(patientRepository, times(1)).findById(1L);
        assertNotNull(foundPatient);
        assertEquals(patient.getFirstName(), foundPatient.getFirstName());
    }

    @Test
    void shouldReturnNullWhenPatientNotFoundById() {
        // Mocking the findById method to return empty Optional
        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        // Calling the service method
        Patient foundPatient = patientService.findById(2L);

        // Verifying the repository interaction and asserting the result
        verify(patientRepository, times(1)).findById(2L);
        assertNull(foundPatient);
    }

    @Test
    void shouldDeletePatientById() {
        // Mocking the deleteById method (it doesn't return anything)
        doNothing().when(patientRepository).deleteById(1L);

        // Calling the service method
        patientService.deleteById(1L);

        // Verifying the repository interaction
        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldSearchPatients() {
        // Mocking the findAll method
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));

        // Calling the service method with a search term
        var result = patientService.searchPatients("John");

        // Verifying the repository interaction and asserting the result
        verify(patientRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getFirstName().contains("John"));
    }
}
