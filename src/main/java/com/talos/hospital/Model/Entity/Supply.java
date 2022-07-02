package com.talos.hospital.Model.Entity;

import com.talos.hospital.Model.Enum.Pretence;
import com.talos.hospital.Model.DTO.Output.SupplyRetrievingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUPPLY")
public class Supply implements SupplyRetrievingDTO {

    @Id
    @GeneratedValue
    @Column(name = "supply_id")
    private UUID supplyId;

    @Pattern(message = "Supply name must not contain any special characters! (Ex.: !,@,#]+", regexp = "[.\\dA-ZÉÁŰŐÚÖÜÓÍa-zéáűőúöüói\\-\\s+]+")
    @Column(name = "supply_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "supply_pretence")
    private Pretence pretence;
    @Column(name = "supply_amount_on_storage")
    private int amountOnStorage;
    @Column(name = "supply_price_without_coverage")
    private int priceWithoutCoverage;
    @Column(name = "supply_price_with_coverage")
    private int priceWithCoverage;

    @PrePersist
    public void autofill() {
        this.setSupplyId(UUID.randomUUID());
    }
}
