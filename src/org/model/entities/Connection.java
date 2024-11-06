package org.model.entities;

import java.util.List;

<<<<<<< HEAD
import org.model.ids.ConnectionID;
=======
import org.model.ids.node.NodeID;
import org.model.ids.connection.ConnectionID;
>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5

/**
 * Represents a connection between nodes in the network.
 */
public class Connection {

    private final ConnectionID id;
<<<<<<< HEAD
    private List<org.model.ids.NodeID> nodesList;
=======
    private List<NodeID> nodesList;
>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5
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
