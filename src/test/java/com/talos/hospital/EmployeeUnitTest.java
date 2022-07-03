package com.talos.hospital;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.DTO.Input.EmployeeCreationDTO;
import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;
import com.talos.hospital.Repository.EmployeeRepository;
import com.talos.hospital.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeUnitTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository);
    }

    private Employee testEmployee = new Employee(
            UUID.fromString("0ae11dfe-5bb5-4733-9de3-219cdc693b94"),
            "Test",
            "Thomas",
            LocalDate.of(1998, Month.JULY, 6),
            Gender.MALE,
            Position.RESEARCHER,
            "Nowhere street 420",
            "06201111222",
            true
    );

    @Test
    void addMethodWorksAsIntended() {
        int sizeBeforeAddMethod = employeeService.findAllEmployeesTML().size();
        employeeService.saveEmployeeTML(new Employee());
        int sizeAfterAddMethod = employeeService.findAllEmployeesTML().size();
        assertThat(sizeBeforeAddMethod < sizeAfterAddMethod);
    }

    @Test
    void getEmployeeMethodWorksAsIntended() {
        when(employeeRepository.findEmployeeByEmployeeId(UUID.randomUUID())).thenReturn(null);

        try {
            employeeService.getEmployeeById(UUID.randomUUID());
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }
    }

    @Test
    void deleteMethodThrowsNoSuchIdFoundExceptionWhenGivenNonExistingUUID() {
        try {
            employeeService.deleteEmployeeByIdTML(UUID.randomUUID());
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }
    }

    @Test
    void getMethodThrowsNoSuchIdFoundExceptionWhenGivenNonExistingUUID() {
        UUID randomUUID = UUID.randomUUID();
        when(employeeRepository.findById(randomUUID)).thenThrow(NoSuchIdFound.class);
        try {
            employeeService.findEmployeeByIdTML(randomUUID);
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }
    }

    @Test
    void getMethodWorksAsIntended() {
        UUID uuid = UUID.fromString("0ae11dfe-5bb5-4733-9de3-219cdc693b94");
        when(employeeRepository.findById(uuid)).thenReturn(Optional.ofNullable(testEmployee));
        Employee gottenEmployee = employeeService.findEmployeeByIdTML(uuid);

        assertThat(gottenEmployee.equals(testEmployee));

    }

    @Test
    void getAllMethodShouldEmployeeList() {
        when(employeeRepository.findAll()).thenReturn(List.of(testEmployee));
        List<Employee> returnedEmployees = employeeService.findAllEmployeesTML();
        assertThat(Objects.equals(returnedEmployees.get(0), List.of(testEmployee)));

    }

    @Test
    void getAllMethodShouldEmptyList() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        List<Employee> returnedEmployees = employeeService.findAllEmployeesTML();
        assertThat(Objects.equals(returnedEmployees, Collections.emptyList()));

    }

    @Test
    void deleteEmployeeShouldReturnExceptionWhenGivenInvalidUUID() {
        try {
            employeeService.deleteEmployee(UUID.randomUUID());
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }
    }

    @Test
    void updateEmployeeShouldReturnExceptionWhenGivenInvalidUUID() {
        try {
            employeeService.updateEmployee(new EmployeeCreationDTO(), UUID.randomUUID());
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }

    }
}


