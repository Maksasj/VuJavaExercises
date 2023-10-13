package com.radioboos.poke_pedia.common;

import javafx.scene.image.Image;

public class Iconable {
    private final Image image;

    public Iconable(String path) {
        if(Utils.fileExist(path)) {
            this.image  = new Image(path);
        } else {
            this.image = new Image("C:\\Programming\\java\\poke_pedia\\src\\main\\resources\\com\\radioboos\\poke_pedia\\icons\\unable_to_find_icon.png");
        }
    }

    public Image getImage() {
        return image;
    }
}
