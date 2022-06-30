package com.talos.hospital.Service;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.*;
import com.talos.hospital.Repository.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;

    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public List<SupplyCreationDTO> findAllSupplies() {
        return supplyRepository.findAll().stream()
                .map(this::convertSupplyToCreationDTO)
                .collect(Collectors.toList());
    }

    public SupplyRetrievingDTO addSupply(SupplyCreationDTO supply) {
        Supply newSupply = new Supply();
        newSupply.setSupplyId(supply.getSupplyId());
        newSupply.setName(supply.getName());
        newSupply.setPretence(supply.getPretence());
        newSupply.setPriceWithCoverage(supply.getPriceWithCoverage());
        newSupply.setPriceWithoutCoverage(supply.getPriceWithoutCoverage());
        newSupply.setAmountOnStorage(supply.getAmountOnStorage());
        return supplyRepository.save(newSupply);
    }

    public SupplyRetrievingDTO getSupplyById(UUID id) {
        return supplyRepository.findById(id).orElseThrow(() -> new NoSuchIdFound(Supply.class.getSimpleName(), id));
    }


    public SupplyCreationDTO updateSupply(SupplyCreationDTO supply, UUID supplyUUID) {
        Supply newSupply = supplyRepository.findById(supplyUUID).orElseThrow(() -> new NoSuchIdFound(Supply.class.getSimpleName(), supplyUUID));
        newSupply.setName(supply.getName());
        newSupply.setPretence(supply.getPretence());
        newSupply.setPriceWithCoverage(supply.getPriceWithCoverage());
        newSupply.setPriceWithoutCoverage(supply.getPriceWithoutCoverage());
        newSupply.setAmountOnStorage(supply.getAmountOnStorage());
        supplyRepository.save(newSupply);
        return convertSupplyToCreationDTO(newSupply);

    }

    public void deleteSupply(UUID supplyUUID) {
        Supply supply = supplyRepository.findById(supplyUUID).orElseThrow(() -> new NoSuchIdFound(Supply.class.getSimpleName(), supplyUUID));
        supplyRepository.deleteById(supply.getSupplyId());
    }

    public SupplyCreationDTO convertSupplyToCreationDTO(Supply supply) {
        SupplyCreationDTO supplyCreationDTO = new SupplyCreationDTO();
        supplyCreationDTO.setSupplyId(supply.getSupplyId());
        supplyCreationDTO.setName(supply.getName());
        supplyCreationDTO.setPretence(supply.getPretence());
        supplyCreationDTO.setPriceWithCoverage(supply.getPriceWithCoverage());
        supplyCreationDTO.setPriceWithoutCoverage(supply.getPriceWithoutCoverage());
        supplyCreationDTO.setAmountOnStorage(supply.getAmountOnStorage());
        return supplyCreationDTO;
    }

    public SupplyRetrievingDTO convertSupplyToRetrievingDTO(Supply supply) {

        return new SupplyRetrievingDTO() {
            @Override
            public UUID getSupplyId() {
                return supply.getSupplyId();
            }

            @Override
            public String getName() {
                return supply.getName();
            }

            @Override
            public Pretence getPretence() {
                return supply.getPretence();
            }
        };
    }
}
