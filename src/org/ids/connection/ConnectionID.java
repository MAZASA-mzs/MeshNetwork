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
}   

// public abstract class AbstractIDGenerator {

//     /**
//      * Generates a new UUID-based ID.
//      * @return a new UUID.
//      */
//     public UUID generate() {
//         return UUID.randomUUID();
//     }
// }
