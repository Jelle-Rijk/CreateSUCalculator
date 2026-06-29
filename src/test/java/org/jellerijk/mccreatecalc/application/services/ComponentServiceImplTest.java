package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.entities.components.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentServiceImplTest {
    ComponentRepository compRepo;
    GeneratorRepository generatorRepository;
    ComponentServiceImpl c;

    @BeforeEach
    void setUp() {
        generatorRepository = mock();
        compRepo = mock();
        c = new ComponentServiceImpl(generatorRepository, compRepo);
    }


    @Test
    void getConsumerByName_ConsumerExists_ReturnsOptionalWithValue() {
        when(compRepo.getConsumerByName("Plug")).thenReturn(Optional.of(mock()));
        assertTrue(c.getConsumerByName("Plug").isPresent());
    }

    @Test
    void getConsumerByName_ConsumerDoesNotExist_ReturnsEmptyOptional() {
        when(compRepo.getConsumerByName("Plug")).thenReturn(Optional.empty());
        assertTrue(c.getConsumerByName("Plug").isEmpty());
    }

    @Test
    void getAllGeneratorOptions_DelegatesToRepository() {
        Generator gen1 = new WaterWheel(WaterWheelType.LARGE);
        Generator gen2 = new WindmillImpl(0);
        when(compRepo.getAllGenerators()).thenReturn(List.of(gen1, gen2));
        assertEquals(2, c.getAllGeneratorOptions().size());
    }
}