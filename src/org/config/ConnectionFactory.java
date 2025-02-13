package org.config;

import java.util.Random;

import org.model.containers.ConnectionStorage;
import org.model.entity.Connection;
import org.model.ids.ConnectionID;
import org.model.implementations.base.connections.BaseConnection;

public class ConnectionFactory {
    private static Random random = new Random();

    public static ConnectionID newPtPConnection() {
        Connection connection = new BaseConnection(true, random.nextInt(100, 200), random.nextInt(999, 1000));
        ConnectionStorage.add(connection);
        return connection.getID();
    }    
}
