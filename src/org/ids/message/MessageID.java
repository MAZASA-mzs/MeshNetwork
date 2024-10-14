package org.ids.message;

import java.util.UUID;

import org.abstracts.AbstractID;

/**
 * Represents a unique identifier for a Message.
 */
public class MessageID extends AbstractID {

    public MessageID() {
        super();
    }

    public MessageID(UUID id) {
        super(id);
    }
    
    @Override
    public MessageID copy() {
        return new MessageID(id);
    }
}
