package data;

import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public interface TestEmployees {

    Employee THOMAS = new Employee(UUID.fromString("39d4b9ba-f73b-489c-af00-f38020549cdb"),
            "Thomas",
            "Andersen",
            LocalDate.of(1922, Month.APRIL,10),
            Gender.MALE,
            Position.FELLOW,
            "Szeged, Dugonics tér 13, 6720",
            "06202222222",
            true);

    Employee INVALIDTHOMAS = new Employee(UUID.fromString("8cd5807a-d32d-4cd5-beea-d0c992a8deda"),
            "Tho23mas",
            "Ander32sen",
            LocalDate.of(1922, Month.APRIL,10),
            Gender.MALE,
            Position.FELLOW,
            "Szeged, Dugonics tér 13, 6720",
            "06202222222",
            true);

    Employee Johns = new Employee(UUID.fromString("a90511ff-8a98-4f8f-92b4-63c6feea45ac"),
            "Philip",
            "Morris",
            LocalDate.of(1922, Month.APRIL,10),
            Gender.MALE,
            Position.FELLOW,
            "Budapest, Rákóczi tér 25, 1047",
            "06203333333",
            true);
}
