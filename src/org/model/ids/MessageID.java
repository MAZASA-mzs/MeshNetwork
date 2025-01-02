package org.model.ids;

import java.util.UUID;

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
