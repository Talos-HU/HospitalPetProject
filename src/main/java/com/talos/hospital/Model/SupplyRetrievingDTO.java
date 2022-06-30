package com.talos.hospital.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;


public interface SupplyRetrievingDTO {

    UUID getSupplyId();
    String getName();
    @Enumerated(EnumType.STRING)
    Pretence getPretence();


}
