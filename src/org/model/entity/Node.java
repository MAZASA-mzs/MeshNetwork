package org.model.entity;

import java.util.List;
import java.util.Set;
import java.util.Collections;

import org.model.abstracts.AbstractBehavior;
import org.model.abstracts.AbstractNetworkMgr;
import org.model.abstracts.AbstractProtocol;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
import org.model.ids.PacketID;
import org.model.structures.Message;
import org.model.structures.Packet;

/**
 * Represents a node in the network.
 */
public class Node {

    private final NodeID        id;
    private double              x;
    private double              y;
    AbstractProtocol    protocol;
    AbstractBehavior    behavior;
    AbstractNetworkMgr  networkMgr;

    public Node(double x, double y, AbstractProtocol protocol, AbstractBehavior behavior, AbstractNetworkMgr networkMgr) {
        this.id = new NodeID(); 
        this.x = x;
        this.y = y;
        this.protocol = protocol;
        this.behavior = behavior;
        this.networkMgr = networkMgr;
    }

    public NodeID getID() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Set<ConnectionID> getConnectionsSet() {
        return Collections.unmodifiableSet(this.networkMgr.getConnectionsSet());
    }

    public void tick() {
        networkMgr.tick();
        behavior.tick();
        List<Message> messages = behavior.getNewMessages();
        protocol.sendMessages(messages);
        protocol.tick();        
    }

    public void connectionBreak(ConnectionID id) {
        networkMgr.connectionBreak(id);
    }

    public boolean connectionRequest(ConnectionID id) {
        return networkMgr.connectionRequest(id);
    }

    public void recivePackets(List<Packet> packets) {
        protocol.receivePackets(packets);
    }

    public void sendingPacketResult(PacketID id, boolean isSuccessfulSending) {
        protocol.sendingPacketResult(id, isSuccessfulSending);
    }
}
