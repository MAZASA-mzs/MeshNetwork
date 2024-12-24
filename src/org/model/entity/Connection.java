package org.model.entity;

import java.util.Set;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import org.model.structures.Packet;
import org.model.structures.Pair;
import org.controller.DataCollector;
import org.model.containers.NodeStorage;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
import org.model.implementation.simple.SimpleProtocolData;

/**
 * Represents a connection between nodes in the network.
 */
public class Connection {

    private final ConnectionID id;
    private final boolean      isPtP;
    private int                speed;
    private double             reliability;
    private Set<NodeID>        connectedNodesID;
    private List<Pair<Packet, Integer>> sendingPacketsList;

    public Connection(boolean isPtP, int speed, double reliability) {
        this.id = new ConnectionID();
        this.isPtP = isPtP;
        this.speed = speed;
        this.reliability = reliability;
        this.connectedNodesID = new HashSet<NodeID>();
        this.sendingPacketsList = new ArrayList<Pair<Packet, Integer>>();
    }

    public ConnectionID getID() {
        return id;
    }
    
    public boolean isPtP() {
        return isPtP;
    }

    public int getSpeed() {
        return speed;
    }

    public double getReliability() {
        return reliability;
    }

    public void removeNodeID(NodeID nodeID) {
        this.connectedNodesID.remove(nodeID);
    }

    public void addNodeID(NodeID nodeID) {
        this.connectedNodesID.add(nodeID);
    }

    public Set<NodeID> getConnectedNodesID() {
        return Collections.unmodifiableSet(this.connectedNodesID);
    }

    public void tick() {
        DataCollector.connectionBetweenNodes(this.getID(), this.getConnectedNodesID());
        List<Pair<Packet, Integer>> newSendingPacketsList = new LinkedList<>();
        for (Pair<Packet, Integer> pair : this.sendingPacketsList) {
            Packet packet = pair.k;
            SimpleProtocolData data = (SimpleProtocolData)packet.getProtocolData();
            int sendingProgress = pair.v;
            DataCollector.packetesInConnection(this.getID(), data.getMessageID(), packet.getID(), sendingProgress);

            if (sendingProgress >= 1000 ) {
                Node node = NodeStorage.get(packet.getDestination());
                node.protocol.receivePacket(packet);
            }
            else {
                if (new Random().nextInt(1000) > this.reliability) {
                    DataCollector.packetLost(this.getID(), packet.getID());    
                    continue;
                }
                newSendingPacketsList.addLast(new Pair<Packet,Integer>(packet, sendingProgress+this.speed));
            }
        }
        this.sendingPacketsList = newSendingPacketsList;
    }

    public void sendPackets(List<Packet> packets) {
        for (Packet packet : packets)
            sendingPacketsList.addLast(new Pair<Packet,Integer>(packet, 0));
    }
}
