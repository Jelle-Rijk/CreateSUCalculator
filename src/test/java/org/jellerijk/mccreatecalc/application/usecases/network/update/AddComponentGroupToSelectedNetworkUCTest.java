package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.ComponentService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddComponentGroupToSelectedNetworkUCTest {
    private ComponentService componentService;
    private NetworkService networkService;
    private AddComponentGroupToSelectedNetworkUC uc;
    private static final AddComponentGroupRequest VALID_REQUEST = new AddComponentGroupRequest(ComponentType.WATER_WHEEL, WaterWheelType.SMALL, null);

    @BeforeEach
    void setUp() {
        networkService = mock();
        componentService = mock();
        StressNetwork network = StressNetwork.Builder.aStressNetwork()
                .withId("1234")
                .withConsumers(new ArrayList<>())
                .withGenerators(new ArrayList<>())
                .withName("Test")
                .build();
        when(networkService.getSelectedNetwork()).thenReturn(Optional.of(network));
        uc = new AddComponentGroupToSelectedNetworkUC(networkService, componentService);
    }

    @Test
    void execute_NoNetworkSelected_ThrowsNoSuchElementException() {
        when(networkService.getSelectedNetwork()).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> uc.execute(VALID_REQUEST));
    }

    @Test
    void execute_WaterWheel_ReturnsCorrectComponentGroupDTO() {
        AddComponentGroupRequest request = new AddComponentGroupRequest(ComponentType.WATER_WHEEL, WaterWheelType.SMALL, null);
        assertEquals(ComponentType.WATER_WHEEL, uc.execute(request).type());
    }

    @Test
    void execute_Windmill_ReturnsCorrectComponentGroupDTO() {
        AddComponentGroupRequest request = new AddComponentGroupRequest(ComponentType.WINDMILL, null, null);
        assertEquals(ComponentType.WINDMILL, uc.execute(request).type());
    }

    @Test
    void execute_Consumer_ReturnsCorrectComponentGroupDTO() {
        AddComponentGroupRequest request = new AddComponentGroupRequest(ComponentType.CONSUMER, null, "Cuckoo Clock");
        Consumer cuckooClock = mock();
        when(componentService.getConsumerByName("Cuckoo Clock")).thenReturn(Optional.of(cuckooClock));
        assertEquals(ComponentType.CONSUMER, uc.execute(request).type());
    }

    @Test
    void execute_ConsumerDoesNotExistInDatabase_ThrowsNoSuchElementException() {
        AddComponentGroupRequest request = new AddComponentGroupRequest(ComponentType.CONSUMER, null, "Cluck Clock");
        when(componentService.getConsumerByName("Cluck Clock")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> uc.execute(request));
    }
}