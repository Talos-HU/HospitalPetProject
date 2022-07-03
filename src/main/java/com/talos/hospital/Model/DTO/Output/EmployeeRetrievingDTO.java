package com.talos.hospital.Model.DTO.Output;

import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.UUID;

public interface EmployeeRetrievingDTO extends Serializable {

    UUID getEmployeeId();

    String getFirstName();

    String getLastName();

    Gender getGender();

    @Enumerated(EnumType.STRING)
    Position getPosition();

}
