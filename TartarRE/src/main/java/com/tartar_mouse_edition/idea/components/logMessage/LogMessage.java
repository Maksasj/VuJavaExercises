/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.tartar_mouse_edition.idea.components.logMessage;

public class LogMessage {
    public LogLevel level;
    public String messages;
    public long timestamp;

    public LogMessage(LogLevel level, String messages) {
        this.level = level;
        this.messages = messages;
        this.timestamp = System.currentTimeMillis();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessages() {
        return messages;
    }
}
