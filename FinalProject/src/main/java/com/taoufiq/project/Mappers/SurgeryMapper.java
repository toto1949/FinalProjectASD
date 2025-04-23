package com.taoufiq.project.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.taoufiq.project.DTOs.SurgeryDTO;
import com.taoufiq.project.Models.Surgery;

@Mapper
public interface SurgeryMapper {
    SurgeryMapper INSTANCE = Mappers.getMapper(SurgeryMapper.class);

    SurgeryDTO surgeryToSurgeryDTO(Surgery surgery);
    Surgery surgeryDTOToSurgery(SurgeryDTO surgeryDTO);
}
