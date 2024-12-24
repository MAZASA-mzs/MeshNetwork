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
    private static Map<ConnectionID, Connection> data = new HashMap<ConnectionID, Connection>();

    private ConnectionStorage() {
        throw new UnsupportedOperationException("This class cannot be instantiated, it's static.");
    }

    public static Connection get(ConnectionID connectionID) {
        return data.get(connectionID);
    }

    public static void add(Connection item) {
        data.put(item.getID(), item);
    }

    public static void remove(ConnectionID id) {
        data.remove(id);
    }

    public static Set<ConnectionID> getIDs() {
        return data.keySet();
    }

    public static void update() {
        for (ConnectionID connectionID : ConnectionStorage.getIDs()) {
            ConnectionStorage.get(connectionID).tick();
        }
        return;
    }
}
