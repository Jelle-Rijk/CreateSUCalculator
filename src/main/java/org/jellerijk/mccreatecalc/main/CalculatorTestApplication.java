package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jellerijk.mccreatecalc.application.services.*;
import org.jellerijk.mccreatecalc.application.usecases.network.read.GetComponentGroupUC;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.presentation.componentgroup.ComponentGroupController;

import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        UseCaseFactory factory = mock();
        GetComponentGroupUC componentGroupUC = mock();
        ComponentGroupDTO group = new ComponentGroupDTO("1234", ComponentType.CONSUMER, "Water Wheel", "create_water_wheel.png", 3, 0, null, 3 * 256, 8);
        when(componentGroupUC.execute("1234")).thenReturn(group);

        ComponentGroupController controller = new ComponentGroupController(factory, "1234");
        return (Parent) controller.getView();
    }
}
