package entities;

import ids.NodeID;
import java.util.List;

/**
 * Represents a node in the network.
 */
public class Node {

    private final NodeID id;
    private Position position;
    private List<ids.ConnectionID> connectionsList;
    private abstract.AbstractProtocol protocol;
    private abstract.AbstractBehavior behavior;
    private abstract.AbstractNetworkMgr networkMgr;

    public Node() {
        this.id = new NodeID(); // Generates a new NodeID
    }

    public NodeID getId() {
        return id;
    }

    public void tick() {
        // Logic for the tick
    }

    // Getters and Setters for position, protocol, behavior, etc.
}
