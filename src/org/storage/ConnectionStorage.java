package org.storage;

import org.abstracts.AbstractStorage;
import org.entities.Connection;
import org.ids.connection.ConnectionID;

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
