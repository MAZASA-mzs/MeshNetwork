package entities;

import ids.ConnectionID;
import java.util.List;

/**
 * Represents a connection between nodes in the network.
 */
public class Connection {

    private final ConnectionID id;
    private List<ids.NodeID> nodesList;
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
