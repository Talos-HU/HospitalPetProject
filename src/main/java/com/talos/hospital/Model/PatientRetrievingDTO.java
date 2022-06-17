package com.talos.hospital.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface PatientRetrievingDTO {

    UUID getPatientId();

    String getFirstName();

    String getLastName();

    LocalDate getBirthDate();

    LocalDate getAdmissionDate();

    EmployeeRetrievingDTO getDoctor();

    List<SupplyDTO> getListOfSupplies();


}
