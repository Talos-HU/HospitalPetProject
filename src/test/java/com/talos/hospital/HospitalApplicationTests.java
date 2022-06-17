package com.talos.hospital;

import com.talos.hospital.Model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospitalApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String url = "/employee";

    @Test
    void testGetAllEmployees() {
        Employee[] expectedList = {};

        for (Employee employee : expectedList) {
            postEmployeeIntoUrl(employee, url);
        }

        ResponseEntity<Employee> listResponseEntity = testRestTemplate.getForEntity(url,Employee.class);

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        assertEquals(expectedList, listResponseEntity.getBody());

    }


    private void postEmployeeIntoUrl(Employee employee, String url) {
        HttpEntity<Employee> httpEntity = createHttpEntityFromEmployee(employee);
        testRestTemplate.postForEntity(url, httpEntity, UUID.class);
    }

    private HttpEntity<Employee> createHttpEntityFromEmployee(Employee employee) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(employee, httpHeaders);
    }
}
