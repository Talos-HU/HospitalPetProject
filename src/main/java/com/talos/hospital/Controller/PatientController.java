package com.talos.hospital.Controller;


import com.talos.hospital.Model.Patient;
import com.talos.hospital.Model.PatientCreatingDTO;
import com.talos.hospital.Model.PatientRetrievingDTO;
import com.talos.hospital.Service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<PatientRetrievingDTO> listAllPatients() {
        return patientService.listAllPatients();
    }

    @PostMapping()
    public PatientRetrievingDTO addPatient(@RequestBody PatientCreatingDTO patientCreatingDTO) {
        return patientService.addPatient(patientCreatingDTO);
    }


    @GetMapping("/{id}")
    public PatientRetrievingDTO getPatientById(@PathVariable("id") UUID id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Optional<PatientRetrievingDTO> updatePatient(@RequestBody Patient patient, @PathVariable("id") UUID patientUUID) {
        return patientService.updatePatient(patient,patientUUID);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") UUID patientUUID) {
        patientService.deletePatient(patientUUID);
    }

    @PostMapping("/{id}/addSupplies/{supplyUUID}")
    public void addSuppliesToPatient(@PathVariable("id") UUID patientUUID,@PathVariable("supplyUUID") UUID supplyUUID) {
        patientService.addSuppliesToPatient(patientUUID,supplyUUID);
    }
}
