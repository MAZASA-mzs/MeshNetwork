<<<<<<<< HEAD:src/org/model/ids/ConnectionID.java
package org.model.ids;
========
package org.model.ids.connection;
>>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5:src/org/model/ids/connection/ConnectionID.java

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
