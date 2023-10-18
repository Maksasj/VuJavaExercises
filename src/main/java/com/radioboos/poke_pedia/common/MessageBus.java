package com.radioboos.poke_pedia.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MessageBus {
    static private final MessageBus INSTANCE = new MessageBus();
    private Stack<Object> messages;

    private MessageBus() {
        messages = new Stack<>();
    }

    public static MessageBus getInstance() {
        return INSTANCE;
    }

    public void pushData(Object object) {
        messages.add(object);
    }

    public Object popData() {
        if(messages.size() == 0) {
            return null;
        }

        return messages.pop();
    }
}
