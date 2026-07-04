package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.data.local.SelectedNetwork;
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
class NetworkServiceImplTest {
    @Mock
    private NetworkRepository networkRepo;
    private SelectedNetworkData selectedNetwork;
    @Mock
    private SelectedNetworkPublisher selectedPublisher;

    private NetworkServiceImpl ns;

    @BeforeEach
    void setUp() {
        selectedNetwork = new SelectedNetwork();
        ns = new NetworkServiceImpl(networkRepo, selectedNetwork, selectedPublisher);
    }

    @Test
    void getById_ValidId_ReturnsStressNetwork() {
        StressNetwork n = mock();
        when(networkRepo.getById("test-id")).thenReturn(Optional.of(n));
        assertTrue(ns.getById("test-id").isPresent());
    }

    @Test
    void getById_IdNotInDatabase_ReturnsEmptyOptional() {
        when(networkRepo.getById("test-id")).thenReturn(Optional.empty());
        assertTrue(ns.getById("test-id").isEmpty());
    }

    @Test
    void getSelectedNetwork_NoNetworkSelected_ReturnsEmptyOptional() {
        assertTrue(ns.getSelectedNetwork().isEmpty());
    }

    @Test
    void getSelectedNetwork_NetworkSelected_ReturnsFilledOptional() {
        selectedNetwork.write(mock());
        assertTrue(ns.getSelectedNetwork().isPresent());
    }

    @Test
    void subscribeToSelectedNetwork() {
        SelectedNetworkObserver observer = mock();
        ns.subscribeToSelectedNetwork(observer);
        verify(selectedPublisher).subscribe(observer);
    }

    @Test
    void setSelectedNetwork_SetsNetworkCorrectly() {
        StressNetwork n = mock();
        ns.setSelectedNetwork(n);
        assertEquals(Optional.of(n), ns.getSelectedNetwork());
    }

    @Test
    void setSelectedNetwork_NetworkIsNull_SetsCorrectly() {
        ns.setSelectedNetwork(null);
        assertTrue(ns.getSelectedNetwork().isEmpty());
    }

    @Test
    void setSelectedNetwork_ValidNetwork_PublishesNetwork() {
        StressNetwork n = mock();
        ns.setSelectedNetwork(n);
        verify(selectedPublisher).publish(n);
    }

    @Test
    void setSelectedNetwork_Null_PublishesNullToObservers() {
        ns.setSelectedNetwork(null);
        verify(selectedPublisher).publish(null);
    }

    @Test
    void save_ValidNetwork() {
        StressNetwork n = mock();
        ns.save(n);
        verify(networkRepo).update(n);
    }

    @Test
    void save_NetworkIsNull_ThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> ns.save(null));
    }

    @Test
    void add_ValidNetwork() {
        StressNetwork n = mock();
        ns.add(n);
        verify(networkRepo).add(n);
    }

    @Test
    void add_NetworkIsNull_ThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> ns.add(null));
    }

    @Test
    void getAllNamesAndIds_SavedNetworks_ReturnsListWithAllNetworks() {
        when(networkRepo.getInfoForAllNetworks()).thenReturn(List.of(new NetworkInfo("123", "Test1"), new NetworkInfo("456", "Test2")));
        assertEquals(2, ns.getAllNamesAndIds().size());
    }

    @Test
    void getAllNamesAndIds_NoSavedNetworks_ReturnsEmptyList() {
        when(networkRepo.getInfoForAllNetworks()).thenReturn(new ArrayList<>());
        assertTrue(ns.getAllNamesAndIds().isEmpty());
    }

    @Test
    void delete_delegatesCallToRepo() {
        ns.delete("id");
        verify(networkRepo).delete("id");
    }

    @Test
    void delete_DeletedNetworkWasSelectedNetwork_SetsSelectedNetworkToNull() {
        StressNetwork network = new StressNetwork("test-id", "test-network", new ArrayList<>(), new ArrayList<>());
        selectedNetwork.write(network);
        ns.delete("test-id");
        assertTrue(ns.getSelectedNetwork().isEmpty());
    }

    @Test
    void delete_DeletedNetworkWasNotSelectedNetwork_LeavesSelectedNetwork() {
        StressNetwork network = StressNetwork.Builder.aStressNetwork()
                .withId("test-id")
                .withName("test-name")
                .withConsumers(new ArrayList<>())
                .withGenerators(new ArrayList<>())
                .build();
        selectedNetwork.write(network);
        ns.delete("not-test-id");
        assertEquals("test-id", ns.getSelectedNetwork().orElseThrow().getId());
    }
}