package com.talos.hospital.Controller;

import com.talos.hospital.Model.DTO.Input.EmployeeCreationDTO;
import com.talos.hospital.Model.DTO.Output.EmployeeRetrievingDTO;
import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Service.EmployeeService;
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
@RequestMapping("employee")
@Tag(name = "Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Operation(summary = "Returns a list of Employees",
            responses = {
                    @ApiResponse(description = "Get Employees",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class)))
            })
    @GetMapping()
    public List<EmployeeRetrievingDTO> listAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @Operation(summary = "If the data is valid, adds an Employee to the database.",
            responses = {
                    @ApiResponse(description = "Add Employee",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),

                    @ApiResponse(description = "Fail to add Employee",
                            responseCode = "400",
                            content = @Content)
            })
    @PostMapping()
    public ResponseEntity<EmployeeRetrievingDTO> addEmployee(@Valid @RequestBody EmployeeCreationDTO employeeCreationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for creating a new Employee.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(employeeService.addEmployee(employeeCreationDTO));
    }


    @Operation(summary = "If the Employee exists, returns it.",
            responses = {
                    @ApiResponse(description = "Get Employee",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),

                    @ApiResponse(description = "Fail to get Employee",
                            responseCode = "400",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public EmployeeCreationDTO getEmployeeById(@PathVariable("id") UUID employeeUUID) {
        return employeeService.getEmployeeById(employeeUUID);
    }

    @Operation(summary = "If the Employee exists, updates it with given valid data.",
            responses = {
                    @ApiResponse(description = "Update Employee",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),

                    @ApiResponse(description = "Employee not found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Invalid data",
                            responseCode = "400",
                            content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRetrievingDTO> updateEmployee(@Valid @RequestBody EmployeeCreationDTO employeeCreationDTO, BindingResult bindingResult, @PathVariable("id") UUID employeeUUID) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for editing current Employee.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(employeeService.updateEmployee(employeeCreationDTO, employeeUUID));
    }

    @Operation(summary = "If the Employee exists, deletes it.",
            responses = {
                    @ApiResponse(description = "Delete Employee",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),

                    @ApiResponse(description = "Employee not found",
                            responseCode = "404",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") UUID employeeUUID) {
        employeeService.deleteEmployee(employeeUUID);
    }
}
