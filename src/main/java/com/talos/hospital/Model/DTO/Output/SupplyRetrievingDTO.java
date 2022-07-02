package com.talos.hospital.Model.DTO.Output;

import com.talos.hospital.Model.Enum.Pretence;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;


public interface SupplyRetrievingDTO {

    UUID getSupplyId();
    String getName();
    @Enumerated(EnumType.STRING)
    Pretence getPretence();


}
