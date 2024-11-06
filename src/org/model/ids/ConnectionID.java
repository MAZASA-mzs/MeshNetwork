package org.model.ids;

import java.util.UUID;

import org.model.abstracts.AbstractID;

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
