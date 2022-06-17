package com.talos.hospital.Service;

import com.talos.hospital.Model.*;
import com.talos.hospital.Repository.EmployeeRepository;
import com.talos.hospital.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;

    public PatientService(PatientRepository patientRepository, EmployeeRepository employeeRepository) {
        this.patientRepository = patientRepository;
        this.employeeRepository = employeeRepository;
    }


    public List<PatientRetrievingDTO> listAllPatients() {
        return patientRepository.getAllBy();
//        return patientRepository.findAll().stream()
//                .map(patients -> PatientRetrievingDTO.builder()
//                        .patientId(patients.getPatientId())
//                        .firstName(patients.getFirstName())
//                        .lastName(patients.getLastName())
//                        .birthDate(patients.getBirthDate())
//                        .admissionDate(patients.getAdmissionDate())
//                        .symptomsAtAdmission(patients.getSymptomsAtAdmission())
//                        .listOfSupplies(convertSupplyListToDtoList(patients.getListOfSupplies()))
//                        .doctor(
//                                EmployeeRetrievingDTO.builder()
//                                        .lastName(patients.getDoctor().getLastName())
//                                        .gender(patients.getDoctor().getGender())
//                                        .position(patients.getDoctor().getPosition())
//                                        .firstName(patients.getDoctor().getFirstName()).build()).build()).collect(Collectors.toList());
    }

    public PatientRetrievingDTO addPatient(PatientCreatingDTO patientCreatingDTO) {
        Patient patient = new Patient();
        patient.setPatientId(patientCreatingDTO.getPatientId());
        patient.setFirstName(patientCreatingDTO.getFirstName());
        patient.setLastName(patientCreatingDTO.getLastName());
        patient.setBirthDate(patientCreatingDTO.getBirthDate());
        patient.setAdmissionDate(patientCreatingDTO.getAdmissionDate());
        patient.setSymptomsAtAdmission(patientCreatingDTO.getSymptomsAtAdmission());
        patient.setDoctor(employeeRepository.findById(patientCreatingDTO.getEmployeeUUID()).get());
        patientRepository.save(patient);
        return null;
//
//        EmployeeRetrievingDTO employeeDTO = EmployeeRetrievingDTO.builder().firstName(patient.getDoctor().getFirstName())
//                .lastName(patient.getDoctor().getLastName())
//                .gender(patient.getDoctor().getGender())
//                .position(patient.getDoctor().getPosition()).build();
//
//
//        return PatientRetrievingDTO.builder()
//                .firstName(patient.getFirstName())
//                .lastName(patient.getLastName())
//                .birthDate(patient.getBirthDate())
//                .admissionDate(patient.getAdmissionDate())
//                .symptomsAtAdmission(patient.getSymptomsAtAdmission())
//                .doctor(employeeDTO).build();
    }

    public PatientRetrievingDTO getPatientById(UUID id) {
        Patient patient = patientRepository.findById(id).get();
        return getPatientRetrievingDTO(patient);
    }

    public void deletePatient(UUID employeeId) {
        patientRepository.deleteById(employeeId);
    }

    public Optional<PatientRetrievingDTO> updatePatient(Patient patient, UUID patientUUID) {
            return patientRepository.findById(patientUUID).map(updatingPatient -> {
                updatingPatient.setAdmissionDate(patient.getAdmissionDate());
                updatingPatient.setFirstName(patient.getFirstName());
                updatingPatient.setLastName(patient.getLastName());
                updatingPatient.setBirthDate(patient.getBirthDate());
                updatingPatient.setDoctor(patient.getDoctor());
                updatingPatient.setSymptomsAtAdmission(patient.getSymptomsAtAdmission());
                updatingPatient.setListOfSupplies(patient.getListOfSupplies());
                patientRepository.save(updatingPatient);
                return getPatientRetrievingDTO(patient);
            });
}

    private PatientRetrievingDTO getPatientRetrievingDTO(Patient patient) {
        return null;
//        return PatientRetrievingDTO.builder()
//                .patientId(patient.getPatientId())
//                .firstName(patient.getFirstName())
//                .lastName(patient.getLastName())
//                .birthDate(patient.getBirthDate())
//                .admissionDate(patient.getAdmissionDate())
//                .symptomsAtAdmission(patient.getSymptomsAtAdmission())
//                .doctor(
//                        EmployeeRetrievingDTO.builder()
//                                .lastName(patient.getDoctor().getLastName())
//                                .gender(patient.getDoctor().getGender())
//                                .position(patient.getDoctor().getPosition())
//                                .firstName(patient.getDoctor().getFirstName()).build()).build();
    }

    private List<SupplyDTO> convertSupplyListToDtoList(List<Supply> supplyList) {
        return  null;
//        return supplyList.stream()
//                .map(dto -> new SupplyDTO(dto.getSupplyId(),dto.getName(),dto.getPretence()))
//                .collect(Collectors.toList());
    }

    public void addSuppliesToPatient(UUID patientUUID, UUID supplyUUID) {
        patientRepository.addSuppliesToPatient(patientUUID,supplyUUID);
    }
}
