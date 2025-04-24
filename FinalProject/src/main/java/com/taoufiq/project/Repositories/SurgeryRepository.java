package com.taoufiq.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taoufiq.project.Models.Surgery;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {}


