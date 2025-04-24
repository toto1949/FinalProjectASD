package com.taoufiq.project.Controllers;

import com.taoufiq.project.Models.Appointment;
import com.taoufiq.project.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/dentist/{dentistId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDentist(@PathVariable Long dentistId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDentistId(dentistId));
    }

    @GetMapping("/surgery/{surgeryId}")
    public ResponseEntity<List<Appointment>> getAppointmentsBySurgery(@PathVariable Long surgeryId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsBySurgeryId(surgeryId));
    }

    @GetMapping("/patient/{patientId}/date/{date}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientAndDate(
            @PathVariable Long patientId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientIdAndDate(patientId, date));
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        appointment.setId(id);
        return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }
} 