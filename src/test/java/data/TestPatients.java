package data;

import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.Entity.Patient;
import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.UUID;

public interface TestPatients {

    Patient Robert = new Patient(
            UUID.fromString("238afa42-3f48-4307-8647-3d8a15ceea6d"),
            "Robert",
            "Kovacs",
            LocalDate.of(1989,Month.NOVEMBER,22),
            LocalDate.now(),
            "Nothing",
            new Employee(UUID.fromString("5b7315fa-569b-4f08-8e59-06527fb53f14"),
                    "Thomas",
                    "Andersen",
                    LocalDate.of(1922, Month.APRIL,10),
                    Gender.MALE,
                    Position.FELLOW,
                    "Szeged, Dugonics tér 13, 6720",
                    "06202222222",
                    true),
            Collections.emptyList()

    );

    Patient Norbert = new Patient(
            UUID.fromString("5bc6e23c-657f-4f3f-a609-c187c14a7762"),
            "Norbert",
            "Iród",
            LocalDate.of(1996,Month.AUGUST,1),
            LocalDate.now(),
            "coughing blood",
            new Employee(UUID.fromString("5b7315fa-569b-4f08-8e59-06527fb53f14"),
                    "Thomas",
                    "Andersen",
                    LocalDate.of(1922, Month.APRIL,10),
                    Gender.MALE,
                    Position.FELLOW,
                    "Szeged, Dugonics tér 13, 6720",
                    "06202222222",
                    true),
            Collections.emptyList()

    );

    Patient Adam = new Patient(
            UUID.fromString("cae8e9bc-fc1a-483c-a8b2-b85a69fc1b25"),
            "Adam",
            "Ostoros",
            LocalDate.of(1999,Month.DECEMBER,24),
            LocalDate.now(),
            "loss of sight in right eye",
            new Employee(UUID.fromString("5b7315fa-569b-4f08-8e59-06527fb53f14"),
                    "Thomas",
                    "Andersen",
                    LocalDate.of(1922, Month.APRIL,10),
                    Gender.MALE,
                    Position.FELLOW,
                    "Szeged, Dugonics tér 13, 6720",
                    "06202222222",
                    true),
            Collections.emptyList()

    );

    Patient Ferenc = new Patient(
            UUID.fromString("196b8d9a-c067-4efc-963f-c7bf53f220c9"),
            "Ferenc",
            "Tell",
            LocalDate.of(1949,Month.FEBRUARY,12),
            LocalDate.now(),
            "high blood pressure",
            new Employee(UUID.fromString("5b7315fa-569b-4f08-8e59-06527fb53f14"),
                    "Thomas",
                    "Andersen",
                    LocalDate.of(1922, Month.APRIL,10),
                    Gender.MALE,
                    Position.FELLOW,
                    "Szeged, Dugonics tér 13, 6720",
                    "06202222222",
                    true),
            Collections.emptyList()

    );

    Patient Anna = new Patient(
            UUID.fromString("4e7787f2-15e1-4950-81ad-2632fc6189ef"),
            "An2424242na",
            "Kovacs",
            LocalDate.of(1975,Month.JANUARY,17),
            LocalDate.now(),
            "low blood pressure",
            new Employee(UUID.fromString("5b7315fa-569b-4f08-8e59-06527fb53f14"),
                    "Thomas",
                    "Andersen",
                    LocalDate.of(1922, Month.APRIL,10),
                    Gender.MALE,
                    Position.FELLOW,
                    "Szeged, Dugonics tér 13, 6720",
                    "06202222222",
                    true),
            Collections.emptyList()

    );

}
