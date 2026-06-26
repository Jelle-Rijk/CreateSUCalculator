package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;
import org.jellerijk.mccreatecalc.entities.GeneratorEntry;
import org.jellerijk.mccreatecalc.presentation.generatorEntry.GeneratorEntryController;

import java.util.Objects;

public class CalculatorTestApplication extends Application {
    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setCenter(buildRoot());
        BorderPane.setMargin(root.getCenter(), new Insets(25));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Test application");
        stage.show();
    }

    private Parent buildRoot() {
        GeneratorEntryController controller = new GeneratorEntryController();
        ConstantGenerator generator = new ConstantGenerator("Water wheel", "create_water_wheel.png", 256, 8);
        GeneratorEntry entry = new GeneratorEntry(generator, 4);
        controller.setEntry(entry);
        return (Parent) controller.getView();
    }
}
