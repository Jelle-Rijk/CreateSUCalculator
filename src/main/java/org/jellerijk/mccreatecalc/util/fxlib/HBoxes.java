package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class HBoxes {
    public static HBox aligned(Pos alignment, double gap, Node... nodes) {
        HBox hb = new HBox(gap, nodes);
        hb.setAlignment(alignment);
        return hb;
    }
}
