package org.model.ids;

import java.util.UUID;

import org.model.abstracts.AbstractID;

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
}