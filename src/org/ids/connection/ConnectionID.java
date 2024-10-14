package org.ids.connection;

import java.util.UUID;

import org.abstracts.AbstractID;

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
    
    @Override
    public ConnectionID copy() {
        return new ConnectionID(id);
    }
}   
