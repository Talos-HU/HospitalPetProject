package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.Employee;
import com.talos.hospital.Model.EmployeeCreationDTO;
import com.talos.hospital.Model.EmployeeRetrievingDTO;
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
        return employeeRepository.getEmployeeByEmployeeId(id).orElseThrow(() -> new NoSuchIdFound(Employee.class.getSimpleName(),id));
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
        employeeRepository.deleteById(employeeUUID);
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

    public Employee convertCreationDtoToEmployee(EmployeeCreationDTO employeeCreationDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeCreationDTO.getEmployeeId());
        employee.setFirstName(employeeCreationDTO.getFirstName());
        employee.setLastName(employeeCreationDTO.getLastName());
        employee.setBirthDate(employeeCreationDTO.getBirthDate());
        employee.setGender(employeeCreationDTO.getGender());
        employee.setPosition(employeeCreationDTO.getPosition());
        employee.setAge(employeeCreationDTO.getAge());
        employee.setAddress(employeeCreationDTO.getAddress());
        employee.setPhoneNumber(employeeCreationDTO.getPhoneNumber());
        employee.setStatus(employeeCreationDTO.getStatus());
        return employee;
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
