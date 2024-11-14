package org.model.containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.model.entity.Connection;
import org.model.ids.ConnectionID;

/**
 * Storage for managing connections in the network.
 */
public class ConnectionStorage {
    private Map<ConnectionID, Connection> data = new HashMap<ConnectionID, Connection>();

    public Connection get(ConnectionID connectionId) {
        return this.data.get(connectionId);
    }

    public void add(Connection item) {
        data.put(item.getId(), item);
    }

    public void remove(ConnectionID id) {
        data.remove(id);
    }

    public Set<ConnectionID> getAllIds() {
        return data.keySet();
    }

    public void update() {
        return;
    }
}
