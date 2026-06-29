package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.data.dao.ConsumerDAO;
import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.jellerijk.mccreatecalc.entities.components.Windmill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

}