package com.talos.hospital;

import com.talos.hospital.Model.Entity.Supply;
import org.flywaydb.core.Flyway;
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

import static data.TestSupplies.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class HospitalSupplyTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String entityUrl;

    @BeforeEach
    void setUp() {
        entityUrl = "http://localhost:" +
                port + "/supply/";
    }

    @BeforeEach
    void deleteMethodClearsEveryEntityCreatedByFlyway() {
        restTemplate.delete("http://localhost:" + port + "/patient/b1cebfa7-68ca-4eea-be57-fbfee8faec62");
        restTemplate.delete("http://localhost:" + port + "/supply/5dc3e5f3-278c-41f5-bbaa-69f14e08dcb4");
        restTemplate.delete("http://localhost:" + port + "/supply/cd49510e-e1f2-4ea2-8b7c-2dd9dd407a4b");
        restTemplate.delete("http://localhost:" + port + "/supply/098bd847-b16c-4cd3-bb06-d589718521fd");
        restTemplate.delete("http://localhost:" + port + "/supply/674c80e3-d57a-4cfe-be92-0b9a06f8e7ce");
    }

    @Test
    void getMethodShouldReturnSpecifiedSupplyWithCorrectName() {
        UUID uuidOfPostedSupply = restTemplate.postForObject(entityUrl, ASPIRIN, Supply.class).getSupplyId();
        Supply supplyGotByGetMethod = restTemplate.getForObject(entityUrl + uuidOfPostedSupply, Supply.class);
        assertEquals(ASPIRIN.getName(), supplyGotByGetMethod.getName());
    }

    @Test
    void postMethodShouldAddSupplyCorrectly() {
        Supply testSupply = restTemplate.postForObject(entityUrl, ASPIRIN, Supply.class);
        assertEquals(ASPIRIN.getName(), testSupply.getName());
    }

    @Test
    void postMethodShouldReturnBadRequestAfterTryingToAddSupplyWithBadData() {
        ResponseEntity<Supply> response = restTemplate.postForEntity(entityUrl, INVALIDMEDICINE, Supply.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMethodShouldReturnNotFoundAfterTryingToGetSupplyWithIncorrectUUID() {
        String testUUID = UUID.randomUUID().toString();
        ResponseEntity<Supply> testSupply = restTemplate.getForEntity(entityUrl + testUUID, Supply.class);
        assertEquals(HttpStatus.NOT_FOUND, testSupply.getStatusCode());
    }

    @Test
    void deleteMethodShouldDeleteSupplyWithGivenUUID() {
        UUID uuidOfPostedSupply = restTemplate.postForObject(entityUrl, ASPIRIN, Supply.class).getSupplyId();
        restTemplate.delete(entityUrl + uuidOfPostedSupply, Supply.class);
        assertNull(restTemplate.getForObject(entityUrl + uuidOfPostedSupply, Supply.class).getSupplyId());
    }

    @Test
    void deleteMethodShouldNotChangeAnythingWithIncorrectUUID() {
        Integer sizeOfListBeforeDeletion = List.of(restTemplate.getForObject(entityUrl, Supply[].class)).size();
        String testUUID = UUID.randomUUID().toString();
        restTemplate.delete(entityUrl + "/employee/" + testUUID, Supply.class);
        Integer sizeOfListAfterDeletion = List.of(restTemplate.getForObject(entityUrl, Supply[].class)).size();
        assertEquals(sizeOfListBeforeDeletion, sizeOfListAfterDeletion);
    }

    @Test
    void updateMethodShouldChangeTheNameOfSupplyWithCorrectData() {
        UUID uuidOfPostedSupply = restTemplate.postForObject(entityUrl, Supradyn, Supply.class).getSupplyId();
        restTemplate.put(entityUrl + uuidOfPostedSupply, Insuline, Object.class);
        Supply newSupply = restTemplate.getForObject(entityUrl + uuidOfPostedSupply, Supply.class);
        assertEquals(Insuline.getName(), newSupply.getName());
    }

    @Test
    void updateMethodShouldReturnInvalidRequestWithInvalidData() {
        UUID supplyUUID = restTemplate.postForObject(entityUrl, ALGOFLEX, Supply.class).getSupplyId();
        String url = entityUrl + supplyUUID;
        ResponseEntity<Supply> test = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(INVALIDMEDICINE, null), Supply.class);
        assertEquals(HttpStatus.BAD_REQUEST, test.getStatusCode());
    }

}
