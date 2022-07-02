package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.Entity.Supply;
import com.talos.hospital.Model.DTO.Input.SupplyCreationDTO;
import com.talos.hospital.Model.DTO.Output.SupplyRetrievingDTO;
import com.talos.hospital.Repository.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;

    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public List<SupplyRetrievingDTO> findAllSupplies() {
        return supplyRepository.findAllBy();
    }

    public SupplyRetrievingDTO addSupply(SupplyCreationDTO supply) {
        Supply newSupply = Supply
                .builder()
                .name(supply.getName())
                .pretence(supply.getPretence())
                .priceWithCoverage(supply.getPriceWithCoverage())
                .priceWithoutCoverage(supply.getPriceWithoutCoverage())
                .amountOnStorage(supply.getAmountOnStorage())
                .build();

        supplyRepository.save(newSupply);
        return supplyRepository.findSupplyBySupplyId(newSupply.getSupplyId());
    }

    public SupplyCreationDTO getSupplyById(UUID id) {
        Supply supply = supplyRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchIdFound(Supply.class.getSimpleName(), id));

        return SupplyCreationDTO
                .builder()
                .name(supply.getName())
                .pretence(supply.getPretence())
                .amountOnStorage(supply.getAmountOnStorage())
                .priceWithCoverage(supply.getPriceWithCoverage())
                .priceWithoutCoverage(supply.getPriceWithoutCoverage())
                .build();
    }


    public SupplyRetrievingDTO updateSupply(SupplyCreationDTO supply, UUID supplyUUID) {
        Supply newSupply = supplyRepository
                .findById(supplyUUID)
                .orElseThrow(
                        () -> new NoSuchIdFound(Supply.class.getSimpleName(), supplyUUID));

        newSupply.setName(supply.getName());
        newSupply.setPretence(supply.getPretence());
        newSupply.setPriceWithCoverage(supply.getPriceWithCoverage());
        newSupply.setPriceWithoutCoverage(supply.getPriceWithoutCoverage());
        newSupply.setAmountOnStorage(supply.getAmountOnStorage());

        supplyRepository.save(newSupply);
        return supplyRepository.findSupplyBySupplyId(newSupply.getSupplyId());

    }

    public void deleteSupply(UUID supplyUUID) {
        if (supplyRepository.existsById(supplyUUID)) {
            supplyRepository.deleteById(supplyUUID);
        } else {
            throw new NoSuchIdFound(Supply.class.getSimpleName(), supplyUUID);
        }
    }

}
