package com.talos.hospital.Controller;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.EmployeeCreationDTO;
import com.talos.hospital.Model.EmployeeRetrievingDTO;
import com.talos.hospital.Service.EmployeeService;
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
public class EmployeeController {

    private final EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping()
    public List<EmployeeCreationDTO> listAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PostMapping()
    public ResponseEntity<EmployeeCreationDTO> addEmployee(@Valid @RequestBody EmployeeCreationDTO employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for creating a new Employee.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }


    @GetMapping("/{id}")
    public EmployeeRetrievingDTO getEmployeeById(@PathVariable("id") UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeCreationDTO> updatePatient(@Valid @RequestBody EmployeeCreationDTO employee, BindingResult bindingResult, @PathVariable("id") UUID employeeUUID) throws NoSuchIdFound {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid arguments for editing current Employee.");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeUUID));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") UUID employeeUUID) {
        employeeService.deleteEmployee(employeeUUID);
    }
}
