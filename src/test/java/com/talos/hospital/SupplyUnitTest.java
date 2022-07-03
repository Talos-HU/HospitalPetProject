package com.talos.hospital;

import com.talos.hospital.CustomUtils.Exceptions.NoSuchIdFound;
import com.talos.hospital.Model.DTO.Input.SupplyCreationDTO;
import com.talos.hospital.Repository.SupplyRepository;
import com.talos.hospital.Service.SupplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SupplyUnitTest {

    @Mock
    private SupplyRepository supplyRepository;

    private SupplyService supplyService;

    @BeforeEach
    void setUp() {
        supplyService = new SupplyService(supplyRepository);
    }

    @Test
    void updateShouldReturnExceptionWhenGivenNonExistingUUID() {
        try {
            supplyService.updateSupply(new SupplyCreationDTO(), UUID.randomUUID());
        } catch (NoSuchIdFound e) {
            System.out.println("Exception thrown!");
        }
    }

}
