package org.model.structures;

import org.model.ids.MessageID;
import org.model.ids.NodeID;

/**
 * Represents a message sent by nodes.
 */
public class Message {

    private final MessageID id;
    private final NodeID    destination;
    private final int       size;

    public Message(NodeID destination, int size) {
        this.id = new MessageID(); 
        this.destination = destination;
        this.size = size;
    }

    public MessageID getID() {
        return id;
    }
    
    public NodeID getDestination() {
        return destination;
    }

    public int getSize() {
        return size;
    }
}
