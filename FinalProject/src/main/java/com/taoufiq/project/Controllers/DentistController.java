package com.taoufiq.project.Controllers;

import com.taoufiq.project.Models.Dentist;
import com.taoufiq.project.Services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {

    private final DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentists() {
        return ResponseEntity.ok(dentistService.getAllDentistsOrderByLastName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable Long id) {
        return ResponseEntity.ok(dentistService.getDentistById(id));
    }

    @PostMapping
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(dentistService.saveDentist(dentist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> updateDentist(@PathVariable Long id, @RequestBody Dentist dentist) {
        dentist.setId(id);
        return ResponseEntity.ok(dentistService.saveDentist(dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentist(@PathVariable Long id) {
        dentistService.deleteDentist(id);
        return ResponseEntity.ok().build();
    }
} 