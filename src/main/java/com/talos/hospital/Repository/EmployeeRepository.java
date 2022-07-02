package com.talos.hospital.Repository;

import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Model.DTO.Output.EmployeeRetrievingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<EmployeeRetrievingDTO> findAllBy();

    EmployeeRetrievingDTO findEmployeeByEmployeeId(UUID uuid);

}
