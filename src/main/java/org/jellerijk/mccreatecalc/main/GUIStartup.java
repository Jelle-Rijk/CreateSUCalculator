package org.jellerijk.mccreatecalc.main;

import javafx.application.Application;

public class GUIStartup {
    private static final boolean TEST_MODE = true;

    static void main(String[] args) {
        Application.launch(TEST_MODE ? CalculatorTestApplication.class : CalculatorApplication.class, args);
    }
}
