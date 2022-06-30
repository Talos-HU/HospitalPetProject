package com.talos.hospital.Controller;

import com.talos.hospital.Model.SupplyCreationDTO;
import com.talos.hospital.Model.SupplyRetrievingDTO;
import com.talos.hospital.Service.SupplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("supply")
public class SupplyController {

    private final SupplyService supplyService;
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }


    @GetMapping()
    public List<SupplyCreationDTO> ListAllSupplies() {
        return supplyService.findAllSupplies();
    }

    @PostMapping()
    public ResponseEntity<SupplyRetrievingDTO> addSupply(@Valid @RequestBody SupplyCreationDTO supply, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for creating a new Supply.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(supplyService.addSupply(supply));
    }


    @GetMapping("/{id}")
    public SupplyRetrievingDTO getSupplyById(@PathVariable("id") UUID id) {
        return supplyService.getSupplyById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyCreationDTO> updateSupply(@Valid @RequestBody SupplyCreationDTO supply, BindingResult bindingResult, @PathVariable("id") UUID supplyUUID) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for editing current Supply.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(supplyService.updateSupply(supply, supplyUUID));
    }

    @DeleteMapping("/{id}")
    public void deleteSupply(@PathVariable("id") UUID supplyUUID) {
        supplyService.deleteSupply(supplyUUID);
    }

}
