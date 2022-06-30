package com.talos.hospital.Controller;


import com.talos.hospital.Model.PatientCreationDTO;
import com.talos.hospital.Model.PatientRetrievingDTO;
import com.talos.hospital.Service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final PatientService patientService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<PatientRetrievingDTO> listAllPatients() {
        return patientService.listAllPatients();
    }

    @PostMapping()
    public ResponseEntity<PatientCreationDTO> addPatient(@Valid @RequestBody PatientCreationDTO patientCreationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for creating a new Employee.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(patientService.addPatient(patientCreationDTO));
    }


    @GetMapping("/{id}")
    public PatientRetrievingDTO getPatientById(@PathVariable("id") UUID id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientCreationDTO> updatePatient(@Valid @RequestBody PatientCreationDTO patient, BindingResult bindingResult, @PathVariable("id") UUID patientUUID) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for editing current Employee.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(patientService.updatePatient(patient, patientUUID));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") UUID patientUUID) {
        patientService.deletePatient(patientUUID);
    }

    @PostMapping("/{id}/addSupplies/{supplyUUID}")
    public void addSuppliesToPatient(@PathVariable("id") UUID patientUUID, @PathVariable("supplyUUID") UUID supplyUUID) {
        patientService.addSuppliesToPatient(patientUUID, supplyUUID);
    }
}
