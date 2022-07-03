package com.talos.hospital;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;
import com.talos.hospital.Repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class HospitalEmployeeUnitTests {

//    @Autowired
//    private EmployeeRepository testRepository;
//
//    @BeforeEach
//    public void init() {
//        Employee Thomas = new Employee(UUID.fromString("39d4b9ba-f73b-489c-af00-f38020549cdb"),
//                "Thomas",
//                "Andersen",
//                LocalDate.of(1922, Month.APRIL,10),
//                Gender.MALE,
//                Position.FELLOW,
//                "Szeged, Dugonics tér 13, 6720",
//                "06202222222",
//                true);
//
//        Employee Julie = new Employee(UUID.fromString("8cd5807a-d32d-4cd5-beea-d0c992a8deda"),
//                "Julie",
//                "Roberts",
//                LocalDate.of(1922, Month.APRIL,10),
//                Gender.FEMALE,
//                Position.FELLOW,
//                "Szeged, Dugonics tér 13, 6720",
//                "06202222222",
//                true);
//
//        Employee Johns = new Employee(UUID.fromString("a90511ff-8a98-4f8f-92b4-63c6feea45ac"),
//                "Philip",
//                "Morris",
//                LocalDate.of(1922, Month.APRIL,10),
//                Gender.MALE,
//                Position.FELLOW,
//                "Budapest, Rákóczi tér 25, 1047",
//                "06203333333",
//                true);
//        testRepository.saveAll(List.of(Thomas,Julie,Johns));
//
//    }
//
//    @AfterEach
//    public void termin() {
//        testRepository.deleteAll();
//    }
//
//
//    @Test
//    void itShouldFindAllRoomByFloorNumber() {
//        //given
//        Room room = Room.builder()
//                .floor(1)
//                .capacity(1)
//                .build();
//        testRepository.save(room);
//        //when
//        List<Room> allByFloor = testRepository.findAllByFloor(1);
//        //then
//        assertThat(allByFloor.size()).isEqualTo(3);
//
//    }
}


