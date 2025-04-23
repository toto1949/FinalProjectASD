package com.taoufiq.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taoufiq.project.Models.*;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {}

