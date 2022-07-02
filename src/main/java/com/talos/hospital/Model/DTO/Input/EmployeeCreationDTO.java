package com.talos.hospital.Model.DTO.Input;

import com.talos.hospital.CustomUtils.Validations.EmployeeDateConstraint;
import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;
import com.talos.hospital.Util.LocalDateConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreationDTO {

    private UUID employeeId;

    @NotBlank(message = "An Employee must have a first name")
    @Pattern(message = "First name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói]+")
    private String firstName;

    @NotBlank(message = "An Employee must have a last name")
    @Pattern(message = "Last name must only contain letters of the alphabet", regexp = "[A-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói\\-]+")
    private String lastName;

    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "An Employee must have a birth date")
    @EmployeeDateConstraint
    private LocalDate birthDate;

    private Gender gender;

    private Position position;

    private String address;
    private String phoneNumber;
    private Boolean status;
}
