package com.taoufiq.project.Services;

import com.taoufiq.project.Models.Dentist;
import com.taoufiq.project.Repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public List<Dentist> getAllDentistsOrderByLastName() {
        return dentistRepository.findAllOrderByLastNameAsc();
    }

    @Override
    public Dentist getDentistById(Long id) {
        return dentistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentist not found with id: " + id));
    }

    @Override
    public Dentist saveDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public void deleteDentist(Long id) {
        dentistRepository.deleteById(id);
    }
} 