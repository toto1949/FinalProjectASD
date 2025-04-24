package com.taoufiq.project.Services;

import com.taoufiq.project.Models.Appointment;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByDentistId(Long dentistId);
    List<Appointment> getAppointmentsBySurgeryId(Long surgeryId);
    List<Appointment> getAppointmentsByPatientIdAndDate(Long patientId, LocalDate date);
    Appointment saveAppointment(Appointment appointment);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
} 