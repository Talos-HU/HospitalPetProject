package com.talos.hospital.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

public interface EmployeeRetrievingDTO extends Serializable {

    String getFirstName();

    String getLastName();

    Gender getGender();

    @Enumerated(EnumType.STRING)
    Position getPosition();

}
