package org.jellerijk.minecraft.util.fxlib;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViews {
    public static ImageView squareIcon(String imgPath) {
        Image img = new Image(imgPath);
        ImageView iv = new ImageView(img);
        iv.setFitHeight(32);
        iv.setFitWidth(32);
        return iv;
    }
}
