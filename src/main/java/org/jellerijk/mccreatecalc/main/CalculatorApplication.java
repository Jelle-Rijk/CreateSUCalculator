package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.application.services.*;
import org.jellerijk.mccreatecalc.data.database.GeneratorDB;
import org.jellerijk.mccreatecalc.data.database.NetworkDB;
import org.jellerijk.mccreatecalc.data.local.SelectedNetwork;
import org.jellerijk.mccreatecalc.data.repositories.ComponentRepositoryImpl;
import org.jellerijk.mccreatecalc.data.repositories.GeneratorRepositoryImpl;
import org.jellerijk.mccreatecalc.data.repositories.NetworkRepositoryImpl;
import org.jellerijk.mccreatecalc.presentation.networkdetails.NetworkDetailsController;
import org.jellerijk.mccreatecalc.presentation.networklist.NetworkListController;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;

import java.util.Objects;

import static org.mockito.Mockito.mock;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) {
        NetworkService networkService = new NetworkServiceImpl(new NetworkRepositoryImpl(new NetworkDB()), new SelectedNetwork(), new SelectedNetworkPublisher());
        ComponentService componentService = new ComponentServiceImpl(new GeneratorRepositoryImpl(new GeneratorDB()), new ComponentRepositoryImpl(mock()));
        UseCaseFactory networkUCFactory = new UseCaseFactory(networkService, componentService);

        NetworkListController networkListController = new NetworkListController(networkUCFactory);
        NetworkDetailsController detailsController = new NetworkDetailsController(networkUCFactory);
        HBox container = HBoxes.aligned(Pos.CENTER_LEFT, 5, networkListController.getView(), detailsController.getView());
        Scene scene = new Scene(container);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Test application");
        stage.show();
    }
}
