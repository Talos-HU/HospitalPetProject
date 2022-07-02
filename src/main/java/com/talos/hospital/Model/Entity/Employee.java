package com.talos.hospital.Model.Entity;

import com.talos.hospital.CustomUtils.Validations.EmployeeDateConstraint;
import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;
import com.talos.hospital.Util.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {


    @Id
    @Column(name = "employee_id", updatable = false, nullable = false)
    private UUID employeeId;

    @NotBlank(message = "An Employee must have a first name")
    @Column(name = "employee_first_name")
    @Pattern(message = "First name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói]+")
    private String firstName;

    @NotBlank(message = "An Employee must have a last name")
    @Column(name = "employee_last_name")
    @Pattern(message = "Last name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói\\-]+")
    private String lastName;

    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "employee_birthdate")
    @NotNull(message = "An Employee must have a birth date")
    @EmployeeDateConstraint
    private LocalDate birthDate;

    @Column(name = "employee_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "employee_position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "employee_address")
    private String address;

    @Column(name = "employee_phone_number")
    private String phoneNumber;

    @Column(name = "employee_status")
    private Boolean status;

    @PrePersist
    public void autofill() {
        this.setEmployeeId(UUID.randomUUID());
    }

}
