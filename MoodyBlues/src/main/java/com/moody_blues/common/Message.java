/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.UUID;

public class Message implements Serializable {
    private String sender;
    private String text;
    private long timestamp;
    private UUID messageUUID;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;

        this.timestamp = System.currentTimeMillis();
        this.messageUUID = UUID.randomUUID();
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
