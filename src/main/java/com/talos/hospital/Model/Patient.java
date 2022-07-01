package com.talos.hospital.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.talos.hospital.Util.LocalDateConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "patient")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private UUID patientId;

    @Column(name = "patient_first_name")

    @NotBlank(message = "An Employee must have a first name")
    @Pattern(message = "First name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói]+")
    private String firstName;

    @Column(name = "patient_last_name")

    @NotBlank(message = "An Employee must have a first name")
    @Pattern(message = "First name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói]+")
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "patient_birthdate")
    @Convert(converter = LocalDateConverter.class)
    @NotNull(message = "An Employee must have a birth date")
    private LocalDate birthDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "patient_admission_date")
    @Convert(converter = LocalDateConverter.class)
    @NotNull(message = "An Employee must have a birth date")
    private LocalDate admissionDate;

    @Column(name = "patient_symptoms_at_admission")
    private String symptomsAtAdmission;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Employee doctor;

    @OneToMany
    @JsonBackReference
    private List<Supply> listOfSupplies;

    @PrePersist
    public void autofill() {
        this.setPatientId(UUID.randomUUID());
    }

}
