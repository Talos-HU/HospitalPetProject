package com.talos.hospital.Model;

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
