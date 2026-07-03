package org.jellerijk.mccreatecalc.application.usecases.components;

import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.ComponentService;
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
    ComponentService compService;
    private GetConsumersUseCase usecase;

    @BeforeEach
    void setUp() {
        usecase = new GetConsumersUseCase(compService);
    }

    @Test
    void execute_NoComponentsInRepo_ReturnsEmptyList() {
        when(compService.getAllConsumerOptions()).thenReturn(new ArrayList<>());
        assertTrue(usecase.execute().isEmpty());
    }

    @Test
    void execute_ComponentsInRepo_ReturnsListOfSameLength() {
        List<ComponentOption> consumers = List.of(mock(), mock(), mock());
        when(compService.getAllConsumerOptions()).thenReturn(consumers);
        assertEquals(3, usecase.execute().size());
    }
}