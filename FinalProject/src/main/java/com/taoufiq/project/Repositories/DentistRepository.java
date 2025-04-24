package com.taoufiq.project.Repositories;

import com.taoufiq.project.Models.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {
    @Query("SELECT d FROM Dentist d ORDER BY d.name ASC")
    List<Dentist> findAllOrderByLastNameAsc();
}

