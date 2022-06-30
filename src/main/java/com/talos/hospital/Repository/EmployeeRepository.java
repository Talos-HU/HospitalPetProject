package com.talos.hospital.Repository;

import com.talos.hospital.Model.Employee;
import com.talos.hospital.Model.EmployeeRetrievingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<EmployeeRetrievingDTO> getEmployeeByEmployeeId(UUID id);
}
