/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.radioboos.poke_pedia.common;

import java.io.File;

public class Utils {
    static public boolean fileExist(String path) {
        var file = new File(path);
        return file.exists();
    }

    static public float parseFloat(String string, float defaultValue) {
        try {
            return Float.parseFloat(string);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    static public boolean inRange(float min, float max, float value) {
        if(value < min)
            return false;

        if(value > max)
            return false;

        return true;
    }
}
