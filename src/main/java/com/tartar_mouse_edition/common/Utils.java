package com.tartar_mouse_edition.common;

public class Utils {
    static public float clamp(float v, float min, float max) {
        if(v > max) return max;
        if(v < min) return min;

        return v;
    }
}
