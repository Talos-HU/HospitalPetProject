package com.talos.hospital.Repository;

import com.talos.hospital.Model.Patient;
import com.talos.hospital.Model.PatientRetrievingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO patient_list_of_supplies (patient_patient_id, list_of_supplies_supply_id) VALUES (?1,?2)",nativeQuery = true)
    void addSuppliesToPatient(UUID patientUUID, UUID supplyUUID);


    List<PatientRetrievingDTO> getAllBy();
}
