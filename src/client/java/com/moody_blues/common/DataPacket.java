package com.moody_blues.common;

import java.io.Serializable;

public class DataPacket implements Serializable {
    public String message;

    public DataPacket(String message) {
        this.message = message;
    }
}
