package com.talos.hospital.Repository;

import com.talos.hospital.Model.Supply;
import com.talos.hospital.Model.SupplyRetrievingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, UUID> {

    List<SupplyRetrievingDTO> findAllBy();

    SupplyRetrievingDTO findSupplyBySupplyId(UUID supplyId);
}
