package com.talos.hospital.Controller;

import com.talos.hospital.Model.Entity.Supply;
import com.talos.hospital.Model.DTO.Input.SupplyCreationDTO;
import com.talos.hospital.Model.DTO.Output.SupplyRetrievingDTO;
import com.talos.hospital.Service.SupplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Supply")
public class SupplyController {

    private final SupplyService supplyService;
    Logger logger = LoggerFactory.getLogger(SupplyController.class);

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }


    @Operation(summary = "Returns a list of Supplies",
            responses = {
                    @ApiResponse(description = "Get Supplies",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Supply.class)))
            })
    @GetMapping()
    public List<SupplyRetrievingDTO> ListAllSupplies() {
        return supplyService.findAllSupplies();
    }

    @Operation(summary = "If the data is valid, adds a Supply to the database.",
            responses = {
            @ApiResponse(description = "Add Supply",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Supply.class))),

            @ApiResponse(description = "Fail to add Supply",
                    responseCode = "400",
                    content = @Content)
    })
    @PostMapping()
    public ResponseEntity<SupplyRetrievingDTO> addSupply(@Valid @RequestBody SupplyCreationDTO supplyCreationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for creating a new Supply.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(supplyService.addSupply(supplyCreationDTO));
    }


    @Operation(summary = "If the Supply exists, returns it.",
            responses = {
                    @ApiResponse(description = "Get Supply",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Supply.class))),

                    @ApiResponse(description = "Fail to get Supply",
                            responseCode = "400",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public SupplyCreationDTO getSupplyById(@PathVariable("id") UUID supplyUUID) {
        return supplyService.getSupplyById(supplyUUID);
    }

    @Operation(summary = "If the Supply exists, updates it with given valid data.",
            responses = {
                    @ApiResponse(description = "Update Supply",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Supply.class))),

                    @ApiResponse(description = "Supply not found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Invalid data",
                            responseCode = "400",
                            content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<SupplyRetrievingDTO> updateSupply(@Valid @RequestBody SupplyCreationDTO supplyCreationDTO, BindingResult bindingResult, @PathVariable("id") UUID supplyUUID) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for editing current Supply.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(supplyService.updateSupply(supplyCreationDTO, supplyUUID));
    }

    @Operation(summary = "If the Supply exists, deletes it.",
            responses = {
                    @ApiResponse(description = "Delete Supply",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Supply.class))),

                    @ApiResponse(description = "Supply not found",
                            responseCode = "404",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public void deleteSupply(@PathVariable("id") UUID supplyUUID) {
        supplyService.deleteSupply(supplyUUID);
    }

}
