package org.model.entity;

import java.util.List;
import java.util.Set;
import java.util.Collections;

import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
import org.model.ids.PacketID;
import org.model.interfaces.UserNetworkMgr;
import org.model.interfaces.NetworkProtocol;
import org.model.interfaces.UserTrafficBehavior;
import org.model.structures.Message;
import org.model.structures.Packet;

/**
 * Represents a node in the network.
 */
public class Node {

    private final NodeID      id;
    private double             x;
    private double             y;
    NetworkProtocol     protocol;
    UserTrafficBehavior behavior;
    UserNetworkMgr  networkMgr;

    public Node(double x, double y, NetworkProtocol protocol, UserTrafficBehavior behavior, UserNetworkMgr networkMgr) {
        this.id = new NodeID(); 
        this.x = x;
        this.y = y;
        this.protocol = protocol;
        this.behavior = behavior;
        this.networkMgr = networkMgr;
    }

    public Node(NodeID id, double x, double y, NetworkProtocol protocol, UserTrafficBehavior behavior, UserNetworkMgr networkMgr) {
        this.id = id; 
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

    public void receivePacket(Packet packet) {
        protocol.receivePacket(packet);
    }

    public void receivePackets(List<Packet> packets) {
        protocol.receivePackets(packets);
    }

    public void sendingPacketResult(PacketID id, boolean isSuccessfulSending) {
        protocol.sendingPacketResult(id, isSuccessfulSending);
    }

    public String getProtocolState() {
        return this.protocol.getState();
    }

    public String getProtocolType() {
        return this.protocol.getType();
    }

    public String getNetworkMgrState() {
        return this.networkMgr.getState();
    }

    public String getNetworkMgrType() {
        return this.networkMgr.getType();
    }

    public String getBehaviorState() {
        return this.behavior.getState();
    }

    public String getBehaviorType() {
        return this.behavior.getType();
    }
}
