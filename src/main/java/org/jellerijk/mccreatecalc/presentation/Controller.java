package org.jellerijk.mccreatecalc.presentation;

import javafx.concurrent.Task;

public abstract class Controller {
    protected void startTaskOnNewThread(Task<?> task) {
        Thread newThread = new Thread(task);
        newThread.start();
    }
}
