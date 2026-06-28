package org.jellerijk.mccreatecalc.presentation;

import javafx.concurrent.Task;
import javafx.scene.Node;

public abstract class Controller {
    protected void startTaskOnNewThread(Task<?> task) {
        Thread newThread = new Thread(task);
        newThread.start();
    }

    public abstract Node getView();
}
