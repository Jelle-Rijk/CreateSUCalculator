package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViews {
    public static ImageView squareIcon(String imgPath, double size) {
        Image img = new Image(imgPath);
        ImageView iv = new ImageView(img);
        iv.setFitHeight(size);
        iv.setFitWidth(size);
        return iv;
    }
}
