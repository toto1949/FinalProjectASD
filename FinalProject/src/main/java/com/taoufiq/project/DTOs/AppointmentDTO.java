package com.taoufiq.project.DTOs;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private PatientDTO patient;
    private DentistDTO dentist;
    private SurgeryDTO surgery;
}

