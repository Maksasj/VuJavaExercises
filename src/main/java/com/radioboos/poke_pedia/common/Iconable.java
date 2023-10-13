package com.radioboos.poke_pedia.common;

import javafx.scene.image.Image;

public class Iconable {
    private final Image image;

    public Iconable(String path) {
        if(Utils.fileExist(path)) {
            this.image  = new Image(path);
        } else {
            this.image = null;
        }
    }

    public Image getImage() {
        return image;
    }
}
