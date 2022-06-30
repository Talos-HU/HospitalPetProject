package com.talos.hospital.Model;

import java.time.LocalDate;
import java.util.UUID;


public interface PatientRetrievingDTO {

    UUID getPatientId();

    String getFirstName();

    String getLastName();

    LocalDate getBirthDate();

    LocalDate getAdmissionDate();

}
