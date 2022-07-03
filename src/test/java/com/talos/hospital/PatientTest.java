package com.talos.hospital;

import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.Entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static data.TestPatients.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PatientTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String entityUrl;

    @BeforeEach
    void setUp() {
        entityUrl = "http://localhost:" +
                port + "/patient/";
    }

    @BeforeEach
    void deleteMethodClearsEveryEntityCreatedByFlyway() {
    }

    @Test
    void getMethodShouldReturnSpecifiedPatientWithCorrectUUID() {
        UUID uuidOfPostedPatient = restTemplate.postForObject(entityUrl, Robert, Patient.class).getPatientId();
        Patient patientGotByGetMethod = restTemplate.getForObject(entityUrl + uuidOfPostedPatient, Patient.class);
        assertEquals(uuidOfPostedPatient, patientGotByGetMethod.getPatientId());
    }

    @Test
    void getMethodShouldReturnNotFoundAfterTryingToGetPatientWithIncorrectUUID() {
        String testUUID = UUID.randomUUID().toString();
        ResponseEntity<Patient> testEmployee = restTemplate.getForEntity(entityUrl + testUUID, Patient.class);
        assertEquals(HttpStatus.NOT_FOUND, testEmployee.getStatusCode());
    }

    @Test
    void deleteMethodShouldDeletePatientWithGivenUUID() {
        UUID uuidOfPostedEmployee = restTemplate.postForObject(entityUrl, Norbert, Patient.class).getPatientId();
        restTemplate.delete(entityUrl + uuidOfPostedEmployee, Employee.class);
        assertNull(restTemplate.getForObject(entityUrl + uuidOfPostedEmployee, Employee.class).getEmployeeId());
    }

    @Test
    void deleteMethodShouldNotChangeAnythingWithIncorrectUUID() {
        Integer sizeOfListBeforeDeletion = List.of(restTemplate.getForObject(entityUrl, Patient[].class)).size();
        String testUUID = UUID.randomUUID().toString();
        restTemplate.delete(entityUrl + "/patient/" + testUUID, Employee.class);
        Integer sizeOfListAfterDeletion = List.of(restTemplate.getForObject(entityUrl, Patient[].class)).size();
        assertEquals(sizeOfListBeforeDeletion, sizeOfListAfterDeletion);
    }

    @Test
    void updateMethodShouldChangeTheNameOfPatientWithCorrectData() {
        Patient patient = restTemplate.postForObject(entityUrl, Adam, Patient.class);
        String nameBeforeUpdate = patient.getFirstName();
        patient.setFirstName("NemAdam");
        restTemplate.put(entityUrl + patient.getPatientId(), patient, Object.class);
        Patient newPatient = restTemplate.getForObject(entityUrl + patient.getPatientId(), Patient.class);
        assertEquals(newPatient.getFirstName(), nameBeforeUpdate);
    }

    @Test
    void updateMethodShouldReturnInvalidRequestWithInvalidData() {
        UUID patientUUID = restTemplate.postForObject(entityUrl, Ferenc, Patient.class).getPatientId();
        String url = entityUrl + patientUUID;
        ResponseEntity<Patient> test = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(Anna, null), Patient.class);
        assertEquals(HttpStatus.BAD_REQUEST, test.getStatusCode());
    }

}
