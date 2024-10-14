package org.model.storage;

import org.model.abstracts.AbstractStorage;
import org.model.entities.Connection;
import org.model.ids.connection.ConnectionID;

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
