package com.taoufiq.project.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taoufiq.project.Models.Appointment;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDateTime(LocalDateTime appointmentDateTime);

    @Query("SELECT a FROM Appointment a JOIN a.dentist d WHERE d.id = :dentistId")
    List<Appointment> findByDentistId(@Param("dentistId") Long dentistId);

    @Query("SELECT a FROM Appointment a JOIN a.surgery s WHERE s.id = :surgeryId")
    List<Appointment> findBySurgeryId(@Param("surgeryId") Long surgeryId);

    @Query("SELECT a FROM Appointment a JOIN a.patient p WHERE p.id = :patientId AND DATE(a.appointmentDateTime) = :date")
    List<Appointment> findByPatientIdAndDate(@Param("patientId") Long patientId, @Param("date") LocalDate date);
}

