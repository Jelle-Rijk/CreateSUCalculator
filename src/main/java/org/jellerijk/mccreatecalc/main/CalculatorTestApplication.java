package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.application.repositories.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.presentation.controllers.NetworkListController;

import java.util.List;

public class CalculatorTestApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        NetworkListController controller = new NetworkListController(new FetchNetworksInfoUseCase(
                new NetworkRepository() {
                    @Override
                    public void add(StressNetwork network) {
                        System.out.println("Added " + network.name());
                    }

                    @Override
                    public List<NetworkInfo> getInfoForAllNetworks() {
                        return List.of(new NetworkInfo("123", "Network 1"), new NetworkInfo("321", "Netwerker"));
                    }
                }));
        Scene scene = new Scene(controller.getView());

        stage.setScene(scene);
        stage.setTitle("Test application");
        stage.show();
    }
}
