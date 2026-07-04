package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.application.publishers.ComponentGroupDTOPublisher;
import org.jellerijk.mccreatecalc.application.services.*;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.data.database.ComponentGroupDB;
import org.jellerijk.mccreatecalc.data.database.ConsumerDB;
import org.jellerijk.mccreatecalc.data.database.NetworkDB;
import org.jellerijk.mccreatecalc.data.local.SelectedNetwork;
import org.jellerijk.mccreatecalc.data.repositories.ComponentGroupRepositoryImpl;
import org.jellerijk.mccreatecalc.data.repositories.ComponentRepositoryImpl;
import org.jellerijk.mccreatecalc.data.repositories.NetworkRepositoryImpl;
import org.jellerijk.mccreatecalc.presentation.networkdetails.NetworkDetailsController;
import org.jellerijk.mccreatecalc.presentation.networklist.NetworkListController;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;

import java.util.Objects;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) {
        ComponentGroupDAO componentGroupDAO = new ComponentGroupDB();
        NetworkService networkService = new NetworkServiceImpl(new NetworkRepositoryImpl(new NetworkDB(), componentGroupDAO), new SelectedNetwork(), new SelectedNetworkPublisher());
        ComponentService componentService = new ComponentServiceImpl(new ComponentRepositoryImpl(new ConsumerDB()));
        ComponentGroupService componentGroupService = new ComponentGroupServiceImpl(new ComponentGroupRepositoryImpl(componentGroupDAO), new ComponentGroupDTOPublisher());
        UseCaseFactory networkUCFactory = new UseCaseFactory(networkService, componentService, componentGroupService);

        NetworkListController networkListController = new NetworkListController(networkUCFactory);
        NetworkDetailsController detailsController = new NetworkDetailsController(networkUCFactory);
        Node networkList = networkListController.getView();
        Node networkDetails = detailsController.getView();

        HBox container = HBoxes.aligned(Pos.CENTER_LEFT, 5, networkList, networkDetails);
        container.setMinWidth(660);
        Scene scene = new Scene(container);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Test application");
        stage.show();
    }

}
