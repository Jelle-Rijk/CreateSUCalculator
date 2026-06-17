package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.application.repositories.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.presentation.controllers.NetworkDetailsController;
import org.jellerijk.mccreatecalc.presentation.controllers.NetworkListController;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;

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
        NetworkDetailsController detailsController = new NetworkDetailsController();
        HBox container = HBoxes.aligned(Pos.CENTER_LEFT, 5, controller.getView(), detailsController.getView());
        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.setTitle("Test application");
        stage.show();
    }
}
