package com.taoufiq.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taoufiq.project.Models.*;


public interface DentistRepository extends JpaRepository<Patient, Long> {}

