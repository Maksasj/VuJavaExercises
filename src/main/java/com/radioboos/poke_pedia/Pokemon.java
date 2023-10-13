package com.radioboos.poke_pedia;

import com.radioboos.poke_pedia.common.Utils;
import javafx.scene.image.Image;

import java.io.File;

public class Pokemon {
    private Image image;
    private final int id;
    private final String engName;
    private final String jpnName;

    public Pokemon(int id, String engName, String jpnName) {
        this.id = id;
        this.engName = engName;
        this.jpnName = jpnName;

        String formatedPath =
                "C:\\Programming\\java\\poke_pedia\\src\\main\\resources\\com\\radioboos\\poke_pedia\\icons\\"
                + engName.toLowerCase().replace(' ', '-')
                + ".jpg";

        System.out.print(engName);

        if(Utils.fileExist(formatedPath)) {
            this.image  = new Image(formatedPath);
            System.out.print(" exist ! \n");
        } else {
            this.image = null;
            System.out.print(" does not exist ! \n");
        }
    }

    public String getEngName() {
        return engName;
    }

    public String getJpnName() {
        return jpnName;
    }

    public Image getImage() {
        return image;
    }
}
