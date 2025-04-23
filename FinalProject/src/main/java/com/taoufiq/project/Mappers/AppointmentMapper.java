package com.taoufiq.project.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.taoufiq.project.DTOs.AppointmentDTO;
import com.taoufiq.project.Models.Appointment;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
    Appointment appointmentDTOToAppointment(AppointmentDTO appointmentDTO);
}
