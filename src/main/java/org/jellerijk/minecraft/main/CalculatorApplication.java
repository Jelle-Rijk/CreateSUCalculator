package org.jellerijk.minecraft.main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jellerijk.minecraft.gui.view.NetworkDetailScreen;
import org.jellerijk.minecraft.gui.viewModel.DetailedStressNetworkViewModel;

import java.util.Objects;

public class CalculatorApplication extends Application {
    private static final boolean TEST_MODE = true;
    private static final String APPLICATION_TITLE = "Create Mod Stress Calculator";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = TEST_MODE ? createTestRoot() : createRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle(APPLICATION_TITLE);
        stage.show();
    }

    public Parent createTestRoot() {
        DetailedStressNetworkViewModel viewModel = new DetailedStressNetworkViewModel();
        NetworkDetailScreen detailScreen = new NetworkDetailScreen(viewModel);
        return detailScreen;
    }

    public Parent createRoot() {
        return new Label("Hello world!");
    }
}
