package org.model.entities;

import java.util.List;
import java.util.ArrayList;

import org.model.abstracts.AbstractProtocol;
import org.model.ids.node.NodeID;
import org.model.abstracts.AbstractBehavior;
import org.model.abstracts.AbstractNetworkMgr;
/**
 * Represents a node in the network.
 */
public class Node {

    private final NodeID                id;
    private float                       x;
    private float                       y;
    private List<org.model.ids.connection.ConnectionID>  connectionsList;
    private AbstractProtocol            protocol;
    private AbstractBehavior            behavior;
    private AbstractNetworkMgr          networkMgr;

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
