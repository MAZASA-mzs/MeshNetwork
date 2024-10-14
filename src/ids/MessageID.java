package ids;

import abstract.AbstractID;

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
