package org.config;

import java.util.Random;

import org.model.containers.ConnectionStorage;
import org.model.entity.Connection;
import org.model.ids.ConnectionID;

public class ConnectionFactory {
    private static Random random = new Random();

    public static ConnectionID newPtPConnection() {
        Connection connection = new Connection(true, random.nextInt(500, 1000), random.nextInt(500, 1000));
        ConnectionStorage.add(connection);
        return connection.getID();
    }    
}
