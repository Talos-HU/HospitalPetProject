package com.talos.hospital.Model;


import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplyCreationDTO {

    private UUID supplyId;

    @Pattern(message = "Supply name must not contain any special characters! (Ex.: !,@,#]+", regexp = "[.\\dA-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói\\-\\s+]+")
    private String name;

    @Enumerated(EnumType.STRING)
    private Pretence pretence;

    private int amountOnStorage;

    private int priceWithoutCoverage;

    private int priceWithCoverage;



}
