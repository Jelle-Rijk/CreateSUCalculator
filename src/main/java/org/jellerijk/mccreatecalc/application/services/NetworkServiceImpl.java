package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;
import java.util.Optional;

public class NetworkServiceImpl implements NetworkService {
    private final NetworkRepository networkRepo;
    private final SelectedNetworkData selectedNetwork;
    private final SelectedNetworkPublisher selectedNetworkPublisher;

    public NetworkServiceImpl(NetworkRepository networkRepo, SelectedNetworkData selectedNetwork, SelectedNetworkPublisher selectedNetworkPublisher) {
        this.networkRepo = networkRepo;
        this.selectedNetwork = selectedNetwork;
        this.selectedNetworkPublisher = selectedNetworkPublisher;
    }

    @Override
    public Optional<StressNetwork> getById(String id) {
        return networkRepo.getById(id);
    }

    @Override
    public Optional<StressNetwork> getSelectedNetwork() {
        return selectedNetwork.read();
    }

    @Override
    public void subscribeToSelectedNetwork(SelectedNetworkObserver observer) {
        selectedNetworkPublisher.subscribe(observer);
    }

    @Override
    public void setSelectedNetwork(StressNetwork network) {
        selectedNetwork.write(network);
        selectedNetworkPublisher.publish(network);
    }

    @Override
    public void save(StressNetwork network) {
        if (network == null)
            throw new IllegalArgumentException("Cannot save null");
        networkRepo.update(network);
    }

    @Override
    public void add(StressNetwork network) {
        if (network == null)
            throw new IllegalArgumentException("Cannot add null");
        networkRepo.add(network);
    }

    @Override
    public List<NetworkInfo> getAllNamesAndIds() {
        return networkRepo.getInfoForAllNetworks();
    }

    @Override
    public void delete(String networkId) {
        networkRepo.delete(networkId);
        if (selectedNetwork.getId().map(id -> id.equals(networkId)).orElse(false))
            setSelectedNetwork(null);
    }
}
