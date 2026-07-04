package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NetworkListController extends Controller {
    private final NetworkListViewBuilder viewBuilder;
    private final NetworkListInteractor interactor;

    public NetworkListController(UseCaseFactory factory) {
        NetworkListModel model = new NetworkListModel();
        interactor = new NetworkListInteractor(model, factory);
        viewBuilder = new NetworkListViewBuilder(model, this::createNetwork, this::selectNetwork, this::deleteNetwork);
        fetchNetworks();
    }

    private void fetchNetworks() {
        Task<List<NetworkInfo>> fetchTask = new Task<>() {
            @Override
            protected List<NetworkInfo> call() {
                return interactor.fetchNetworks();
            }
        };
        fetchTask.setOnSucceeded(_ -> {
            try {
                interactor.updateNetworks(fetchTask.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        startTaskOnNewThread(fetchTask);
    }

    private void createNetwork() {
        Task<Void> createNetwork = new Task<>() {
            @Override
            protected Void call() {
                interactor.createNetwork();
                return null;
            }
        };
        createNetwork.setOnSucceeded(_ -> fetchNetworks());
        startTaskOnNewThread(createNetwork);
    }

    private void deleteNetwork(String networkId) {
        interactor.deleteNetwork(networkId);
    }

    private void selectNetwork(String networkId) {
        Task<Void> selectNetwork = new Task<>() {
            @Override
            protected Void call() {
                interactor.selectNetwork(networkId);
                return null;
            }
        };
        startTaskOnNewThread(selectNetwork);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
