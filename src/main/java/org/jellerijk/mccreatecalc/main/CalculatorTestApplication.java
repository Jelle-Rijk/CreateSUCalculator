package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.application.usecases.network.NetworkUseCaseFactoryImpl;
import org.jellerijk.mccreatecalc.data.database.NetworkDB;
import org.jellerijk.mccreatecalc.data.local.SelectedNetwork;
import org.jellerijk.mccreatecalc.data.repositories.NetworkRepositoryImpl;
import org.jellerijk.mccreatecalc.presentation.NetworkUseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.controllers.NetworkDetailsController;
import org.jellerijk.mccreatecalc.presentation.controllers.NetworkListController;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;

public class CalculatorTestApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        NetworkUseCaseFactory networkUCFactory = new NetworkUseCaseFactoryImpl(new NetworkRepositoryImpl(new NetworkDB()), new SelectedNetwork());

        NetworkListController networkListController = new NetworkListController(networkUCFactory);
        NetworkDetailsController detailsController = new NetworkDetailsController(networkUCFactory);
        HBox container = HBoxes.aligned(Pos.CENTER_LEFT, 5, networkListController.getView(), detailsController.getView());
        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.setTitle("Test application");
        stage.show();
    }
}
