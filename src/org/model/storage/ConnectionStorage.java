<<<<<<<< HEAD:src/org/model/ConnectionStorage.java
package org.model;

import org.model.abstracts.AbstractStorage;
import org.model.entities.Connection;
import org.model.ids.ConnectionID;
========
package org.model.storage;

import org.model.abstracts.AbstractStorage;
import org.model.entities.Connection;
import org.model.ids.connection.ConnectionID;
>>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5:src/org/model/storage/ConnectionStorage.java

/**
 * Storage for managing connections in the network.
 */
public class ConnectionStorage extends AbstractStorage<Connection> {

    public Connection getConnectionById(ConnectionID connectionId) {
        for (Connection connection : getList()) {
            if (connection.getId().equals(connectionId)) {
                return connection;
            }
        }
        return null;
    }
}
