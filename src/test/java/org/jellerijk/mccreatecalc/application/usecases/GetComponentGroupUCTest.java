package org.jellerijk.mccreatecalc.application.usecases;


import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetComponentGroupUCTest {
    private ComponentGroupRepository componentGroupRepository;
    private GetComponentGroupUC uc;

    @BeforeEach
    void setUp() {
        componentGroupRepository = mock();
        uc = new GetComponentGroupUC(componentGroupRepository);
    }

    @Test
    void execute_ComponentGroupIdInRepository_ReturnsComponentGroupDTO() {
        ComponentGroup cg = ComponentGroup.Builder.aComponentGroup()
                .withComponent(new WaterWheel(WaterWheelType.SMALL))
                .withId("id")
                .withAmount(2)
                .build();
        when(componentGroupRepository.getById("id")).thenReturn(Optional.of(cg));
        assertEquals(ComponentType.WATER_WHEEL, uc.execute("id").type());
    }

    @Test
    void execute_ComponentGroupNotInRepository_ThrowsNoSuchElementException() {
        when(componentGroupRepository.getById("id")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> uc.execute("id"));
    }
}