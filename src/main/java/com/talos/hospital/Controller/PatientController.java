package com.talos.hospital.Controller;


import com.talos.hospital.Model.Entity.Patient;
import com.talos.hospital.Model.DTO.Input.PatientCreationDTO;
import com.talos.hospital.Model.DTO.Output.PatientRetrievingDTO;
import com.talos.hospital.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Patient")
public class PatientController {

    private final PatientService patientService;

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Returns a list of Patients",
            responses = {
            @ApiResponse(description = "Get Patients",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)))
    })
    @GetMapping()
    public List<PatientRetrievingDTO> listAllPatients() {
        return patientService.listAllPatients();
    }

    @Operation(summary = "If the data is valid, adds a Patient to the database.",
            responses = {
                    @ApiResponse(description = "Add Patient",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))),

                    @ApiResponse(description = "Fail to add Patient",
                            responseCode = "400",
                            content = @Content)
            })
    @PostMapping()
    public ResponseEntity<PatientRetrievingDTO> addPatient(@Valid @RequestBody PatientCreationDTO patientCreationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for creating a new Patient.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(patientService.addPatient(patientCreationDTO));
    }


    @Operation(summary = "If the Patient exists, returns it.",
            responses = {
                    @ApiResponse(description = "Get Patient",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))),

                    @ApiResponse(description = "Fail to get Patient",
                            responseCode = "400",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public PatientCreationDTO getPatientById(@PathVariable("id") UUID patientUUID) {
        return patientService.getPatientById(patientUUID);
    }

    @Operation(summary = "If the Patient exists, updates it with given valid data.",
            responses = {
                    @ApiResponse(description = "Update Patient",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))),

                    @ApiResponse(description = "Patient not found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Invalid data",
                            responseCode = "400",
                            content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<PatientRetrievingDTO> updatePatient(@Valid @RequestBody PatientCreationDTO patientCreationDTO, BindingResult bindingResult, @PathVariable("id") UUID patientUUID) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for editing current Patient.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(patientService.updatePatient(patientCreationDTO, patientUUID));
    }

    @Operation(summary = "If the Patient exists, deletes it.",
            responses = {
                    @ApiResponse(description = "Delete Patient",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))),

                    @ApiResponse(description = "Patient not found",
                            responseCode = "404",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") UUID patientUUID) {
        patientService.deletePatient(patientUUID);
    }

    @Operation(summary = "Couples the given existing Patient to the given existing Supply.",
            responses = {
                    @ApiResponse(description = "Add Supply to Patient",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))),

                    @ApiResponse(description = "Patient or Supply not found",
                            responseCode = "404",
                            content = @Content)
            })
    @PostMapping("/{patientUUID}/addSupplies/{supplyUUID}")
    public void addSuppliesToPatient(@PathVariable("patientUUID") UUID patientUUID, @PathVariable("supplyUUID") UUID supplyUUID) {
        patientService.addSuppliesToPatient(patientUUID, supplyUUID);
    }
}
