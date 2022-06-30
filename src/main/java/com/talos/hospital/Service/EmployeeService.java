package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.*;
import com.talos.hospital.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<EmployeeCreationDTO> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertEmployeeToCreationDTO)
                .collect(Collectors.toList());
    }

    public EmployeeCreationDTO addEmployee(EmployeeCreationDTO employee) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setBirthDate(employee.getBirthDate());
        newEmployee.setGender(employee.getGender());
        newEmployee.setPosition(employee.getPosition());
        newEmployee.setAge(employee.getAge());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setStatus(employee.getStatus());
        employeeRepository.save(newEmployee);
        employee.setEmployeeId(newEmployee.getEmployeeId());
        return employee;
    }

    public EmployeeRetrievingDTO getEmployeeById(UUID id) {
        return employeeRepository.getEmployeeByEmployeeId(id).orElseThrow(() -> new NoSuchIdFound(Employee.class.getSimpleName(), id));
    }

    public EmployeeCreationDTO updateEmployee(EmployeeCreationDTO employee, UUID employeeUUID) throws NoSuchIdFound {
        Employee newEmployee = employeeRepository.findById(employeeUUID).orElseThrow(() -> new NoSuchIdFound(Employee.class.getSimpleName(), employeeUUID));
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setBirthDate(employee.getBirthDate());
        newEmployee.setGender(employee.getGender());
        newEmployee.setPosition(employee.getPosition());
        newEmployee.setAge(employee.getAge());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setStatus(employee.getStatus());
        employeeRepository.save(newEmployee);
        return convertEmployeeToCreationDTO(newEmployee);
    }

    public void deleteEmployee(UUID employeeUUID) {
        Employee employee = employeeRepository.findById(employeeUUID).orElseThrow(() -> new NoSuchIdFound(Employee.class.getSimpleName(), employeeUUID));
        employeeRepository.deleteById(employee.getEmployeeId());
    }

    public EmployeeCreationDTO convertEmployeeToCreationDTO(Employee employee) {
        EmployeeCreationDTO employeeCreationDTO = new EmployeeCreationDTO();
        employeeCreationDTO.setEmployeeId(employee.getEmployeeId());
        employeeCreationDTO.setFirstName(employee.getFirstName());
        employeeCreationDTO.setLastName(employee.getLastName());
        employeeCreationDTO.setBirthDate(employee.getBirthDate());
        employeeCreationDTO.setGender(employee.getGender());
        employeeCreationDTO.setPosition(employee.getPosition());
        employeeCreationDTO.setAge(employee.getAge());
        employeeCreationDTO.setAddress(employee.getAddress());
        employeeCreationDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeCreationDTO.setStatus(employee.getStatus());
        return employeeCreationDTO;
    }


    public EmployeeRetrievingDTO convertEmployeeToRetrievingDTO(Employee employee) {

        return new EmployeeRetrievingDTO() {
            @Override
            public String getFirstName() {
                return employee.getFirstName();
            }

            @Override
            public String getLastName() {
                return employee.getLastName();
            }

            @Override
            public Gender getGender() {
                return employee.getGender();
            }

            @Override
            public Position getPosition() {
                return employee.getPosition();
            }
        };
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
