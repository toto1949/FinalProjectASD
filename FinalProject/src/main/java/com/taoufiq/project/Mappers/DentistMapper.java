package com.taoufiq.project.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.taoufiq.project.DTOs.DentistDTO;
import com.taoufiq.project.Models.Dentist;

@Mapper
public interface DentistMapper {
    DentistMapper INSTANCE = Mappers.getMapper(DentistMapper.class);

    DentistDTO dentistToDentistDTO(Dentist dentist);
    Dentist dentistDTOToDentist(DentistDTO dentistDTO);
}
