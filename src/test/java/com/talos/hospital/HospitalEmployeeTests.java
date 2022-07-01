package com.talos.hospital;

import com.talos.hospital.Model.Employee;
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

import static data.TestEmployees.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class HospitalEmployeeTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String entityUrl;

    @BeforeEach
    void setUp() {
        entityUrl = "http://localhost:" +
                port + "/employee/";
    }

    @BeforeEach
    void deleteMethodClearsEveryEntityCreatedByFlyway() {
        restTemplate.delete("http://localhost:" + port + "/patient/b1cebfa7-68ca-4eea-be57-fbfee8faec62");
        restTemplate.delete("http://localhost:" + port + "/supply/5dc3e5f3-278c-41f5-bbaa-69f14e08dcb4");
    }

    @Test
    void getMethodShouldReturnSpecifiedEmployeeWithCorrectUUID() {
        UUID uuidOfPostedEmployee = restTemplate.postForObject(entityUrl, THOMAS, Employee.class).getEmployeeId();
        Employee employeeGotByGetMethod = restTemplate.getForObject(entityUrl + uuidOfPostedEmployee, Employee.class);
        assertEquals(uuidOfPostedEmployee, employeeGotByGetMethod.getEmployeeId());
    }


    @Test
    void getAllMethodShouldReturnCorrectData() {
        Employee testEmployee = restTemplate.postForObject(entityUrl, THOMAS, Employee.class);
        assertEquals(List.of(testEmployee), List.of(restTemplate.getForObject(entityUrl, Employee[].class)));
    }


    @Test
    void postMethodShouldAddEmployeeCorrectly() {
        Employee testEmployee = restTemplate.postForObject(entityUrl, THOMAS, Employee.class);
        assertEquals(THOMAS.getFirstName(), testEmployee.getFirstName());
    }

    @Test
    void postMethodShouldReturnBadRequestAfterTryingToAddEmployeeWithBadData() {
        ResponseEntity<Employee> response = restTemplate.postForEntity(entityUrl, INVALIDTHOMAS, Employee.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMethodShouldReturnNotFoundAfterTryingToGetEmployeeWithIncorrectUUID() {
        String testUUID = UUID.randomUUID().toString();
        ResponseEntity<Employee> testEmployee = restTemplate.getForEntity(entityUrl + testUUID, Employee.class);
        assertEquals(HttpStatus.NOT_FOUND, testEmployee.getStatusCode());
    }

    @Test
    void deleteMethodShouldDeleteEmployeeWithGivenUUID() {
        UUID uuidOfPostedEmployee = restTemplate.postForObject(entityUrl, THOMAS, Employee.class).getEmployeeId();
        restTemplate.delete(entityUrl + uuidOfPostedEmployee, Employee.class);
        assertNull(restTemplate.getForObject(entityUrl + uuidOfPostedEmployee, Employee.class).getEmployeeId());
    }

    @Test
    void deleteMethodShouldNotChangeAnythingWithIncorrectUUID() {
        Integer sizeOfListBeforeDeletion = List.of(restTemplate.getForObject(entityUrl, Employee[].class)).size();
        String testUUID = UUID.randomUUID().toString();
        restTemplate.delete(entityUrl + "/employee/" + testUUID, Employee.class);
        Integer sizeOfListAfterDeletion = List.of(restTemplate.getForObject(entityUrl, Employee[].class)).size();
        assertEquals(sizeOfListBeforeDeletion, sizeOfListAfterDeletion);
    }

    @Test
    void updateMethodShouldChangeTheNameOfEmployeeWithCorrectData() {
        Employee employee = restTemplate.postForObject(entityUrl, THOMAS, Employee.class);
        String nameBeforeUpdate = employee.getFirstName();
        employee.setFirstName("Mattheus");
        restTemplate.put(entityUrl + employee.getEmployeeId(), employee, Object.class);
        Employee newEmployee = restTemplate.getForObject(entityUrl + employee.getEmployeeId(), Employee.class);
        assertEquals(newEmployee.getFirstName(), nameBeforeUpdate);
    }

    @Test
    void updateMethodShouldReturnInvalidRequestWithInvalidData() {
        UUID employeeUUID = restTemplate.postForObject(entityUrl, Johns, Employee.class).getEmployeeId();
        String url = entityUrl + employeeUUID;
        ResponseEntity<Employee> test = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(INVALIDTHOMAS, null), Employee.class);
        assertEquals(HttpStatus.BAD_REQUEST, test.getStatusCode());
    }
}
