package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.data.dao.NetworkDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NetworkRepositoryImplTest {
    @Mock
    private NetworkDAO networkDAO;
    @Mock
    private ComponentGroupDAO cgDAO;
    @Mock
    private StressNetwork network;
    private NetworkRepository n;

    @BeforeEach
    void setUp() {
        n = new NetworkRepositoryImpl(networkDAO, cgDAO);
    }

    @Test
    void add_validNetwork_AddsNetwork() {
        n.add(network);
        verify(networkDAO).insert(network);
    }

    @Test
    void getInfoForAllNetworks_NoNetworks_ReturnsEmptyList() {
        when(networkDAO.loadInfoAndIdsForAllNetworks()).thenReturn(new ArrayList<>());
        assertTrue(n.getInfoForAllNetworks().isEmpty());
    }

    @Test
    void getInfoForAllNetworks_ValidNetworks_ReturnsNetworkInfoList() {
        List<NetworkInfo> list = List.of(mock(), mock(), mock());
        when(networkDAO.loadInfoAndIdsForAllNetworks()).thenReturn(list);
        assertEquals(3, n.getInfoForAllNetworks().size());
    }

    @Test
    void getById_NoNetwork_ReturnsEmptyOptional() {
        String id = "test-id";
        when(networkDAO.getById(id)).thenReturn(Optional.empty());
        assertTrue(n.getById(id).isEmpty());
    }

    @Test
    void getById_ValidNetwork_ReturnsNetwork() {
        String id = "test-id";
        when(networkDAO.getById(id)).thenReturn(Optional.of(new NetworkInfo(id, "Test-Network")));
        assertTrue(n.getById(id).isPresent());
    }

    @Test
    void getById_ValidNetworkWithGenerators_SetsGenerators() {
        String id = "test-id";
        String name = "Test-Network";
        NetworkInfo networkInfo = mock();
        when(networkInfo.id()).thenReturn(id);
        when(networkInfo.name()).thenReturn(name);
        when(networkDAO.getById(id)).thenReturn(Optional.of(networkInfo));

        ComponentGroup generatorGroup1 = mock();
        ComponentGroup generatorGroup2 = mock();
        when(generatorGroup1.isConsumer()).thenReturn(false);
        when(generatorGroup2.isConsumer()).thenReturn(false);
        List<ComponentGroup> groups = List.of(generatorGroup1, generatorGroup2);
        when(cgDAO.getGroupsForNetwork(id)).thenReturn(groups);

        assertEquals(groups, n.getById(id).orElseThrow().generators());
    }

    @Test
    void getById_ValidNetworkWithConsumers_SetsConsumers() {
        String id = "test-id";
        String name = "Test-Network";
        NetworkInfo networkInfo = mock();
        when(networkInfo.id()).thenReturn(id);
        when(networkInfo.name()).thenReturn(name);
        when(networkDAO.getById(id)).thenReturn(Optional.of(networkInfo));

        ComponentGroup consumerGroup1 = mock();
        ComponentGroup consumerGroup2 = mock();
        when(consumerGroup1.isConsumer()).thenReturn(true);
        when(consumerGroup2.isConsumer()).thenReturn(true);
        List<ComponentGroup> groups = List.of(consumerGroup1, consumerGroup2);
        when(cgDAO.getGroupsForNetwork(id)).thenReturn(groups);

        assertEquals(groups, n.getById(id).orElseThrow().consumers());
    }

    @Test
    void getById_ValidNetworkWithConsumersAndGenerators_SetsConsumersCorrectly() {
        String id = "test-id";
        String name = "Test-Network";
        NetworkInfo networkInfo = mock();
        when(networkInfo.id()).thenReturn(id);
        when(networkInfo.name()).thenReturn(name);
        when(networkDAO.getById(id)).thenReturn(Optional.of(networkInfo));

        ComponentGroup consumerGroup1 = mock();
        ComponentGroup consumerGroup2 = mock();
        when(consumerGroup1.isConsumer()).thenReturn(true);
        when(consumerGroup2.isConsumer()).thenReturn(true);
        ComponentGroup generatorGroup1 = mock();
        ComponentGroup generatorGroup2 = mock();
        when(generatorGroup1.isConsumer()).thenReturn(false);
        when(generatorGroup2.isConsumer()).thenReturn(false);
        List<ComponentGroup> groups = List.of(consumerGroup1, consumerGroup2, generatorGroup1, generatorGroup2);
        when(cgDAO.getGroupsForNetwork(id)).thenReturn(groups);

        assertEquals(2, n.getById(id).orElseThrow().consumers().size());
    }

    @Test
    void getById_ValidNetworkWithConsumersAndGenerators_SetsGeneratorsCorrectly() {
        String id = "test-id";
        String name = "Test-Network";
        NetworkInfo networkInfo = mock();
        when(networkInfo.id()).thenReturn(id);
        when(networkInfo.name()).thenReturn(name);
        when(networkDAO.getById(id)).thenReturn(Optional.of(networkInfo));

        ComponentGroup consumerGroup1 = mock();
        ComponentGroup consumerGroup2 = mock();
        when(consumerGroup1.isConsumer()).thenReturn(true);
        when(consumerGroup2.isConsumer()).thenReturn(true);
        ComponentGroup generatorGroup1 = mock();
        ComponentGroup generatorGroup2 = mock();
        when(generatorGroup1.isConsumer()).thenReturn(false);
        when(generatorGroup2.isConsumer()).thenReturn(false);
        List<ComponentGroup> groups = List.of(consumerGroup1, consumerGroup2, generatorGroup1, generatorGroup2);
        when(cgDAO.getGroupsForNetwork(id)).thenReturn(groups);

        assertEquals(2, n.getById(id).orElseThrow().generators().size());
    }
}