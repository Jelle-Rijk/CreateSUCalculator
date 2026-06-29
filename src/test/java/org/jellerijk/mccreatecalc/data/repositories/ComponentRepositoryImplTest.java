package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.data.dao.ConsumerDAO;
import org.jellerijk.mccreatecalc.entities.components.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentRepositoryImplTest {
    private ConsumerDAO consumerDAO;
    private ComponentRepositoryImpl c;

    @BeforeEach
    void setUp() {
        consumerDAO = mock();
        c = new ComponentRepositoryImpl(consumerDAO);
    }

    @Test
    void getAllGenerators_ReturnsList() {
        assertNotNull(c.getAllGenerators());
    }

    @Test
    void getAllGenerators_ContainsAllWaterWheels() {
        assertEquals(WaterWheelType.values().length, c.getAllGenerators()
                .stream()
                .filter(g -> g instanceof WaterWheel)
                .count());
    }

    @Test
    void getAllGenerators_ContainsWindmill() {
        assertTrue(c.getAllGenerators().stream().anyMatch(g -> g instanceof Windmill));
    }

    @Test
    void getAllConsumers_NoneInDatabase_ReturnsEmptyList() {
        assertNotNull(c.getAllConsumers());
    }

    @Test
    void getAllConsumers_ConsumersInDatabase_ReturnsConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(mock());
        consumers.add(mock());
        when(consumerDAO.getAll()).thenReturn(consumers);

        assertEquals(2, c.getAllConsumers().size());
    }

    @Test
    void getConsumerByName_ConsumerInDatabase_ReturnsNonEmptyOptional() {
        String name = "Cuckoo";
        when(consumerDAO.getByName(name)).thenReturn(Optional.of(BaseConsumer.Builder.aBaseConsumer()
                .withName(name)
                .build()));
        assertTrue(c.getConsumerByName(name).isPresent());
    }

    @Test
    void getConsumerByName_ConsumerNotInDatabase_ReturnsEmptyOptional() {
        String name = "Cuckoo";
        when(consumerDAO.getByName(name)).thenReturn(Optional.empty());
        assertTrue(c.getConsumerByName(name).isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "\r", "         "})
    void getConsumerByName_InvalidName_ThrowsIAE(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> c.getConsumerByName(invalidName));
    }

}