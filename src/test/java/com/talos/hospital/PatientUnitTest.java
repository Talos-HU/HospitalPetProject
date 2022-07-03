package com.talos.hospital;
import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.DTO.Input.PatientCreationDTO;
import com.talos.hospital.Model.DTO.Input.SupplyCreationDTO;
import com.talos.hospital.Model.DTO.Output.EmployeeRetrievingDTO;
import com.talos.hospital.Model.DTO.Output.PatientRetrievingDTO;
import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.Entity.Patient;
import com.talos.hospital.Repository.EmployeeRepository;
import com.talos.hospital.Repository.PatientRepository;
import com.talos.hospital.Repository.SupplyRepository;
import com.talos.hospital.Service.EmployeeService;
import com.talos.hospital.Service.PatientService;
import data.TestEmployees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PatientUnitTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private SupplyRepository supplyRepository;

    @Mock
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        patientService = new PatientService(patientRepository,employeeRepository,supplyRepository);
    }



    @Test
    void whenAddPatientWithNonExistingDoctorShouldThrowException() {
        PatientCreationDTO testPatient = new PatientCreationDTO();
        testPatient.setDoctorUUID(UUID.randomUUID());
        try {
            patientService.addPatient(testPatient);
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }
    }

}
