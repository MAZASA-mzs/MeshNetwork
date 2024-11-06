package org.model.entities;

import java.util.List;

import org.model.ids.ConnectionID;

/**
 * Represents a connection between nodes in the network.
 */
public class Connection {

    private final ConnectionID id;
    private List<org.model.ids.NodeID> nodesList;
    private List<Packet> sendingPacketsList;
    private int speed;
    private double reliability;

    public Connection() {
        this.id = new ConnectionID(); // Generates a new ConnectionID
    }

    public ConnectionID getId() {
        return id;
    }

    public void tick() {
        // Logic for the tick
    }

    // Getters and Setters for nodesList, speed, reliability, etc.
}
