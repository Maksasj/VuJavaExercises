package com.radioboos.poke_pedia.common;

import java.io.File;

public class Utils {
    static public boolean fileExist(String path) {
        var file = new File(path);
        return file.exists();
    }
}
