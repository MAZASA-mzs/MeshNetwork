package ids;

import abstracts.AbstractID;

/**
 * Represents a unique identifier for a Connection.
 */
public class ConnectionID extends AbstractID {

    public ConnectionID() {
        super();
    }

    public ConnectionID(UUID id) {
        super(id);
    }
}
