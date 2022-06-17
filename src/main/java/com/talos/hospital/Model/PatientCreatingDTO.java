package com.talos.hospital.Model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreatingDTO {

    private UUID patientId;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate admissionDate;
    private String symptomsAtAdmission;
    private UUID employeeUUID;

}
