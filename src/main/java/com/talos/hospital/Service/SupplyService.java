package com.talos.hospital.Service;

import com.talos.hospital.Model.Supply;
import com.talos.hospital.Repository.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;

    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public List<Supply> findAllSupplies() {
        return supplyRepository.findAll();
    }

    public Supply addSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    public Optional<Supply> getSupplyById(UUID id) {
        return supplyRepository.findById(id);
    }


    public Optional<Supply> updateSupply(Supply supply, UUID supplyUUID) {

        return supplyRepository.findById(supplyUUID).map(updatingSupply -> {
            updatingSupply.setName(supply.getName());
            updatingSupply.setPretence(supply.getPretence());
            updatingSupply.setAmountOnStorage(supply.getAmountOnStorage());
            updatingSupply.setPriceWithoutCoverage(supply.getPriceWithoutCoverage());
            updatingSupply.setPriceWithCoverage(supply.getPriceWithCoverage());
            supplyRepository.save(updatingSupply);
            return updatingSupply;
        });

    }

    public void deleteSupply(UUID supplyUUID) {
        supplyRepository.deleteById(supplyUUID);
    }
}
