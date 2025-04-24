package com.taoufiq.project.Services;

import com.taoufiq.project.Models.Dentist;
import java.util.List;

public interface DentistService {
    List<Dentist> getAllDentistsOrderByLastName();
    Dentist getDentistById(Long id);
    Dentist saveDentist(Dentist dentist);
    void deleteDentist(Long id);
} 