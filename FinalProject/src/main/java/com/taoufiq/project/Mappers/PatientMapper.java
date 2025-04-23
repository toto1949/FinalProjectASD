package com.taoufiq.project.Mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.taoufiq.project.DTOs.PatientDTO;
import com.taoufiq.project.Models.Patient;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO patientToPatientDTO(Patient patient);
    Patient patientDTOToPatient(PatientDTO patientDTO);
}
