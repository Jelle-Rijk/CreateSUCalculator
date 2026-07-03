package org.jellerijk.mccreatecalc.application.usecases.components;

import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetConsumersUseCaseTest {
    @Mock
    ComponentRepository compRepo;
    private GetConsumersUseCase usecase;

    @BeforeEach
    void setUp() {
        usecase = new GetConsumersUseCase(compRepo);
    }

    @Test
    void execute_NoComponentsInRepo_ReturnsEmptyList() {
        when(compRepo.getAllConsumers()).thenReturn(new ArrayList<>());
        assertTrue(usecase.execute().isEmpty());
    }

    @Test
    void execute_ComponentsInRepo_ReturnsListOfSameLength() {
        List<Consumer> consumers = List.of(mock(), mock(), mock());
        when(compRepo.getAllConsumers()).thenReturn(consumers);
        assertEquals(3, usecase.execute().size());
    }
}