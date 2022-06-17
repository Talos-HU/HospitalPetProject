package com.talos.hospital.Controller;

import com.talos.hospital.Model.Employee;
import com.talos.hospital.Model.Supply;
import com.talos.hospital.Service.EmployeeService;
import com.talos.hospital.Service.SupplyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("supply")
public class SupplyController {

    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }



    @GetMapping()
    public List<Supply> ListAllSupplies() {
        return supplyService.findAllSupplies();
    }

    @PostMapping()
    public Supply addSupply(@RequestBody Supply supply) {
        return supplyService.addSupply(supply);
    }


    @GetMapping("/{id}")
    public Optional<Supply> getSupplyById(@PathVariable("id") UUID id) {
        return supplyService.getSupplyById(id);
    }

    @PutMapping("/{id}")
    public Optional<Supply> updateSupply(@RequestBody Supply supply, @PathVariable("id") UUID supplyUUID) {
        return supplyService.updateSupply(supply, supplyUUID);
    }

    @DeleteMapping("/{id}")
    public void deleteSupply(@PathVariable("id") UUID supplyUUID) {
        supplyService.deleteSupply(supplyUUID);
    }

}
