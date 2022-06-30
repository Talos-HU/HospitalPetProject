package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.*;
import com.talos.hospital.Repository.EmployeeRepository;
import com.talos.hospital.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;

    private final SupplyService supplyService;

    public PatientService(PatientRepository patientRepository, EmployeeRepository employeeRepository, EmployeeService employeeService, SupplyService supplyService) {
        this.patientRepository = patientRepository;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.supplyService = supplyService;
    }


    public List<PatientRetrievingDTO> listAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertPatientToRetrievingDTO)
                .collect(Collectors.toList());
    }

    public PatientCreationDTO addPatient(PatientCreationDTO patient) {
        Patient newPatient = new Patient();
        newPatient.setPatientId(patient.getPatientId());
        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setBirthDate(patient.getBirthDate());
        newPatient.setAdmissionDate(patient.getAdmissionDate());
        newPatient.setSymptomsAtAdmission(patient.getSymptomsAtAdmission());
        newPatient.setDoctor(employeeRepository.findById(patient.getDoctorUUID()).orElseThrow());
        newPatient.setListOfSupplies(patient.getListOfSupplies());
        patientRepository.save(newPatient);
        return convertPatientToCreationDTO(newPatient);

    }

    public PatientRetrievingDTO getPatientById(UUID id) {
        return patientRepository.getPatientByPatientId(id).orElseThrow(() -> new NoSuchIdFound(Patient.class.getSimpleName(), id));
    }

    public PatientCreationDTO updatePatient(PatientCreationDTO patient, UUID patientUUID) throws NoSuchIdFound {
        Patient newPatient = patientRepository.findById(patientUUID).orElseThrow(() -> new NoSuchIdFound(Patient.class.getSimpleName(), patientUUID));
        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setBirthDate(patient.getBirthDate());
        newPatient.setAdmissionDate(patient.getAdmissionDate());
        newPatient.setSymptomsAtAdmission(patient.getSymptomsAtAdmission());
        newPatient.setDoctor(employeeRepository.findById(patient.getDoctorUUID()).orElseThrow());
        newPatient.setListOfSupplies(patient.getListOfSupplies());
        patientRepository.save(newPatient);
        return convertPatientToCreationDTO(newPatient);
    }

    public void deletePatient(UUID employeeId) {
        Patient patient = patientRepository.findById(employeeId).orElseThrow(() -> new NoSuchIdFound(Patient.class.getSimpleName(), employeeId));
        patientRepository.deleteById(patient.getPatientId());
    }


    public void addSuppliesToPatient(UUID patientUUID, UUID supplyUUID) {
        patientRepository.addSuppliesToPatient(patientUUID, supplyUUID);
    }

    public PatientCreationDTO convertPatientToCreationDTO(Patient patient) {
        PatientCreationDTO newPatient = new PatientCreationDTO();
        newPatient.setPatientId(patient.getPatientId());
        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setBirthDate(patient.getBirthDate());
        newPatient.setAdmissionDate(patient.getAdmissionDate());
        newPatient.setSymptomsAtAdmission(patient.getSymptomsAtAdmission());
        newPatient.setDoctorUUID(patient.getDoctor().getEmployeeId());
        newPatient.setListOfSupplies(patient.getListOfSupplies());
        return newPatient;
    }

    public PatientRetrievingDTO convertPatientToRetrievingDTO(Patient patient) {
        return new PatientRetrievingDTO() {
            @Override
            public UUID getPatientId() {
                return patient.getPatientId();
            }

            @Override
            public String getFirstName() {
                return patient.getFirstName();
            }

            @Override
            public String getLastName() {
                return patient.getLastName();
            }

            @Override
            public LocalDate getBirthDate() {
                return patient.getBirthDate();
            }

            @Override
            public LocalDate getAdmissionDate() {
                return patient.getAdmissionDate();
            }

            @Override
            public EmployeeRetrievingDTO getDoctor() {
                return employeeService.convertEmployeeToRetrievingDTO(patient.getDoctor());
            }

            @Override
            public List<SupplyRetrievingDTO> getListOfSupplies() {
                List<SupplyRetrievingDTO> listOfMedicine = new ArrayList<>();

                for(Supply supply : patient.getListOfSupplies()) {
                    listOfMedicine.add(supplyService.convertSupplyToRetrievingDTO(supply));
                }

                return listOfMedicine;
            }
        };
    }

}
