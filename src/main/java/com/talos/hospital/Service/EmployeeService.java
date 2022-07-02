package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.DTO.Input.EmployeeCreationDTO;
import com.talos.hospital.Model.DTO.Output.EmployeeRetrievingDTO;
import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<EmployeeRetrievingDTO> findAllEmployees() {
        return employeeRepository.findAllBy();
    }

    public EmployeeRetrievingDTO addEmployee(EmployeeCreationDTO employee) {
        Employee newEmployee = Employee
                .builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .gender(employee.getGender())
                .position(employee.getPosition())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .status(employee.getStatus())
                .build();

        employeeRepository.save(newEmployee);
        return employeeRepository.findEmployeeByEmployeeId(newEmployee.getEmployeeId());
    }

    public EmployeeCreationDTO getEmployeeById(UUID id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchIdFound(Employee.class.getSimpleName(), id));

        return EmployeeCreationDTO
                .builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .gender(employee.getGender())
                .position(employee.getPosition())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .status(employee.getStatus())
                .build();
    }

    public EmployeeRetrievingDTO updateEmployee(EmployeeCreationDTO employee, UUID employeeUUID) throws NoSuchIdFound {
        Employee newEmployee = employeeRepository
                .findById(employeeUUID)
                .orElseThrow(
                        () -> new NoSuchIdFound(Employee.class.getSimpleName(), employeeUUID));

        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setBirthDate(employee.getBirthDate());
        newEmployee.setGender(employee.getGender());
        newEmployee.setPosition(employee.getPosition());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setStatus(employee.getStatus());

        employeeRepository.save(newEmployee);
        return employeeRepository.findEmployeeByEmployeeId(employeeUUID);
    }

    public void deleteEmployee(UUID employeeUUID) {
        if (employeeRepository.existsById(employeeUUID)) {
            employeeRepository.deleteById(employeeUUID);
        } else {
            throw new NoSuchIdFound(Employee.class.getSimpleName(), employeeUUID);
        }
    }

    //TML SECTION
    //Methods beneath only serve the purpose of TML (Thymeleaf)

    public void saveEmployeeTML(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployeesTML() {
        return employeeRepository.findAll();
    }


    public Employee findEmployeeByIdTML(UUID employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public void deleteEmployeeByIdTML(UUID employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
