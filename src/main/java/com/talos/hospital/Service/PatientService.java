package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.*;
import com.talos.hospital.Repository.EmployeeRepository;
import com.talos.hospital.Repository.PatientRepository;
import com.talos.hospital.Repository.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;
    private final SupplyRepository supplyRepository;

    public PatientService(PatientRepository patientRepository, EmployeeRepository employeeRepository, SupplyRepository supplyRepository) {
        this.patientRepository = patientRepository;
        this.employeeRepository = employeeRepository;
        this.supplyRepository = supplyRepository;
    }


    public List<PatientRetrievingDTO> listAllPatients() {
        return patientRepository.getAllBy();
    }

    public PatientRetrievingDTO addPatient(PatientCreationDTO patient) {
        Patient newPatient = Patient
                .builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .birthDate(patient.getBirthDate())
                .admissionDate(patient.getAdmissionDate())
                .symptomsAtAdmission(patient.getSymptomsAtAdmission())
                .doctor(employeeRepository
                        .findById(patient.getPatientId())
                        .orElseThrow(
                        () -> new NoSuchIdFound(Employee.class.getSimpleName(), patient.getDoctorUUID())))
                .listOfSupplies(patient.getListOfSupplies())
                .build();

        patientRepository.save(newPatient);
        return patientRepository.getPatientByPatientId(patient.getPatientId());
    }

    public PatientCreationDTO getPatientById(UUID id) {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchIdFound(Patient.class.getSimpleName(), id));

        return PatientCreationDTO
                .builder()
                .patientId(patient.getPatientId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .birthDate(patient.getBirthDate())
                .doctor(employeeRepository.findEmployeeByEmployeeId(patient.getDoctor().getEmployeeId()))
                .admissionDate(patient.getAdmissionDate())
                .symptomsAtAdmission(patient.getSymptomsAtAdmission())
                .doctorUUID(patient.getDoctor().getEmployeeId())
                .listOfSupplies(patient.getListOfSupplies())
                .build();
    }

    public PatientRetrievingDTO updatePatient(PatientCreationDTO patient, UUID patientUUID) throws NoSuchIdFound {
        Patient newPatient = patientRepository
                .findById(patientUUID)
                .orElseThrow(
                        () -> new NoSuchIdFound(Patient.class.getSimpleName(), patientUUID));

        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setBirthDate(patient.getBirthDate());
        newPatient.setAdmissionDate(patient.getAdmissionDate());
        newPatient.setSymptomsAtAdmission(patient.getSymptomsAtAdmission());
        newPatient.setDoctor(employeeRepository.findById(patient.getDoctorUUID()).orElseThrow());
        newPatient.setListOfSupplies(patient.getListOfSupplies());

        patientRepository.save(newPatient);
        return patientRepository.getPatientByPatientId(newPatient.getPatientId());
    }

    public void deletePatient(UUID patientUUID) {
        if (patientRepository.existsById(patientUUID)) {
            patientRepository.deleteById(patientUUID);
        } else {
            throw new NoSuchIdFound(Patient.class.getSimpleName(), patientUUID);
        }

    }

    public void addSuppliesToPatient(UUID patientUUID, UUID supplyUUID) {
        if (patientRepository.existsById(patientUUID) && supplyRepository.existsById(supplyUUID)) {
            patientRepository.addSuppliesToPatient(patientUUID, supplyUUID);
        } else if (!patientRepository.existsById(patientUUID)) {
            throw new NoSuchIdFound(Patient.class.getSimpleName(), patientUUID);
        } else if (!supplyRepository.existsById(supplyUUID)) {
            throw new NoSuchIdFound(Supply.class.getSimpleName(), supplyUUID);
        }
    }

}
