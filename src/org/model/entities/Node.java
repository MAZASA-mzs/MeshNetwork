package org.model.entities;

import java.util.List;

import org.model.abstracts.AbstractBehavior;
import org.model.abstracts.AbstractNetworkMgr;
import org.model.abstracts.protocol.AbstractProtocol;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
/**
 * Represents a node in the network.
 */
public class Node {

    private final NodeID        ID;
    private float               x;
    private float               y;
    private List<ConnectionID>  connectionsList;
    private AbstractProtocol    protocol;
    private AbstractBehavior    behavior;
    private AbstractNetworkMgr  networkMgr;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public List<ConnectionID> getConnectionsList() {
        return connectionsList;
    }

    public AbstractProtocol getProtocol() {
        return protocol;
    }

    public AbstractBehavior getBehavior() {
        return behavior;
    }

    public AbstractNetworkMgr getNetworkMgr() {
        return networkMgr;
    }

    public Node() {
        this.ID = new NodeID(); // Generates a new NodeID
    }

    public NodeID getID() {
        return ID;
    }

    public void tick() {
        // Logic for the tick
    }

    // Getters and Setters for position, protocol, behavior, etc.
}
