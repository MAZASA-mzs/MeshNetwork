package org.model.entities;

import org.model.ids.MessageID.MessageID;

/**
 * Represents a message that nodes send and receive.
 */
public class Message {

    private final MessageID id;
    private int size;
    private String destination;

    public Message() {
        this.id = new MessageID(); // Generates a new MessageID
    }

    public MessageID getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
