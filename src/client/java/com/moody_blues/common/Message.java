package com.moody_blues.common;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.Instant;

public class Message implements Serializable {
    public String text;
    public Timestamp timestamp;
}
