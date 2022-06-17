package com.talos.hospital.Repository;

import com.talos.hospital.Model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, UUID> {
}
