package com.talos.hospital.Model;

import com.talos.hospital.Util.LocalDateConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreationDTO {

    private UUID patientId;

    @NotBlank(message = "An Employee must have a first name")
    @Pattern(message = "First name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói]+")
    private String firstName;

    @NotBlank(message = "An Employee must have a first name")
    @Pattern(message = "First name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói]+")
    private String lastName;

    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "An Employee must have a birth date")
    private LocalDate birthDate;

    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate admissionDate;

    private EmployeeRetrievingDTO doctor;


    private String symptomsAtAdmission;
    private UUID doctorUUID;
    private List<Supply> listOfSupplies = new ArrayList<>();


}
