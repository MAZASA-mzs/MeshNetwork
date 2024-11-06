package org.model.entities;

<<<<<<< HEAD
import org.model.ids.MessageID.MessageID;
=======
import org.model.ids.message.MessageID;
>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5

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
