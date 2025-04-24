package com.taoufiq.project.Services;

import com.taoufiq.project.Models.Appointment;
import com.taoufiq.project.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAppointmentsByDentistId(Long dentistId) {
        return appointmentRepository.findByDentistId(dentistId);
    }

    @Override
    public List<Appointment> getAppointmentsBySurgeryId(Long surgeryId) {
        return appointmentRepository.findBySurgeryId(surgeryId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientIdAndDate(Long patientId, LocalDate date) {
        return appointmentRepository.findByPatientIdAndDate(patientId, date);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }
} 