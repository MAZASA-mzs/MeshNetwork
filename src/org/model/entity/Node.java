package org.model.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.model.abstracts.AbstractBehavior;
import org.model.abstracts.AbstractNetworkMgr;
import org.model.abstracts.AbstractProtocol;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;

/**
 * Represents a node in the network.
 */
public class Node {

    private final NodeID        id;
    private double              x;
    private double              y;
    private List<ConnectionID>  connectionsList;
    private AbstractProtocol    protocol;
    private AbstractBehavior    behavior;
    private AbstractNetworkMgr  networkMgr;

    public Node(double x, double y, AbstractProtocol protocol, AbstractBehavior behavior, AbstractNetworkMgr networkMgr) {
        this.id = new NodeID(); 
        this.x = x;
        this.y = y;
        this.connectionsList = new ArrayList<ConnectionID>();
        this.protocol = protocol;
        this.behavior = behavior;
        this.networkMgr = networkMgr;
    }

    public NodeID getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public List<ConnectionID> getConnectionsList() {
        return Collections.unmodifiableList(this.connectionsList);
    }

    public void tick() {
        
    }
}
