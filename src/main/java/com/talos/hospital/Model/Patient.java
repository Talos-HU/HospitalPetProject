package com.talos.hospital.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private UUID patientId = UUID.randomUUID();

    @Column(name = "patient_first_name")
    private String firstName;

    @Column(name = "patient_last_name")
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "patient_birthdate")
    private LocalDate birthDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "patient_admission_date")
    private LocalDate admissionDate;
    @Column(name = "patient_symptoms_at_admission")
    private String symptomsAtAdmission;

    @OneToOne
    @JsonIgnore
    private Employee doctor;

    @OneToMany
    @Enumerated(EnumType.STRING)
    @JsonBackReference
    private List<Supply> listOfSupplies;


}
