package com.taoufiq.project.Mappers;


import org.mapstruct.Mapper;

import com.taoufiq.project.DTOs.PatientDTO;
import com.taoufiq.project.Models.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO patientToPatientDTO(Patient patient);
    Patient patientDTOToPatient(PatientDTO patientDTO);
}
