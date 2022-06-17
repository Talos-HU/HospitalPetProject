package com.talos.hospital.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUPPLY")
public class Supply implements SupplyDTO {

    @Id
    @GeneratedValue
    @Column(name = "supply_id")
    private UUID supplyId = UUID.randomUUID();

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
}
