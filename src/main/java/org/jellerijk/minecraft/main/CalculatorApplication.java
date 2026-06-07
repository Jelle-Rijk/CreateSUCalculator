package org.jellerijk.minecraft.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jellerijk.minecraft.viewModel.ComponentViewModel;
import org.jellerijk.minecraft.viewModel.implementations.MockGeneratorVM;
import org.jellerijk.minecraft.views.ComponentSelector;

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
        BorderPane testPane = new BorderPane();
        testPane.setPadding(new Insets(20));
        ObservableList<ComponentViewModel> viewModels = FXCollections.observableArrayList();
        viewModels.add(new MockGeneratorVM("Water wheel"));
        viewModels.add(new MockGeneratorVM("Large water wheel"));

        Node center = new ComponentSelector(viewModels);
        testPane.setCenter(center);
        return testPane;
    }

    public Parent createRoot() {
        return new Label("Hello world!");
    }
}
