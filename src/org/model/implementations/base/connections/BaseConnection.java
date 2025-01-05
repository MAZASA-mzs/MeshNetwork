package org.model.implementations.base.connections;

import java.util.Set;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import org.model.structures.Packet;
import org.model.structures.Pair;
import org.controller.datacollection.DataCollector;
import org.model.containers.NodeStorage;
import org.model.entity.Connection;
import org.model.entity.Node;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
import org.model.implementations.base.meshevents.AddNodeInConnection;
import org.model.implementations.base.meshevents.PacketLost;

/**
 * Represents a connection between nodes in the network.
 */
public class BaseConnection implements Connection {

    private final ConnectionID id;
    private final boolean      isP2P;
    private int                speed;
    private double             reliability;
    private Set<NodeID>        connectedNodesID;
    private List<Pair<Packet, Integer>> sendingPacketsList;

    public BaseConnection(boolean isPtP, int speed, double reliability) {
        this.id = new ConnectionID();
        this.isP2P = isPtP;
        this.speed = speed;
        this.reliability = reliability;
        this.connectedNodesID = new HashSet<NodeID>();
        this.sendingPacketsList = new ArrayList<Pair<Packet, Integer>>();
    }

    @Override
    public ConnectionID getID() {
        return id;
    }
    
    @Override
    public boolean isP2P() {
        return isP2P;
    }

    public int getSpeed() {
        return speed;
    }

    public double getReliability() {
        return reliability;
    }

    @Override
    public void removeNodeID(NodeID nodeID) {
        this.connectedNodesID.remove(nodeID);
    }

    @Override
    public void addNodeID(NodeID nodeID) {
        DataCollector.collect(new AddNodeInConnection(id, nodeID));
        this.connectedNodesID.add(nodeID);
    }

    @Override
    public Set<NodeID> getConnectedNodesID() {
        return Collections.unmodifiableSet(this.connectedNodesID);
    }

    @Override
    public void tick() {
        // DataCollector.connectionBetweenNodes(this.getID(), this.getConnectedNodesID());
        List<Pair<Packet, Integer>> newSendingPacketsList = new LinkedList<>();
        for (Pair<Packet, Integer> pair : this.sendingPacketsList) {
            Packet packet = pair.k;
            int sendingProgress = pair.v;

            if (sendingProgress >= 1000 ) {
                Node node = NodeStorage.get(packet.getDestination());
                node.receivePacket(packet);
            }
            else {
                if (new Random().nextInt(1000) > this.reliability) {
                    DataCollector.collect(new PacketLost(this.getID(), packet.getID()));
                    continue;
                }
                newSendingPacketsList.addLast(new Pair<Packet,Integer>(packet, sendingProgress+this.speed));
            }
        }
        this.sendingPacketsList = newSendingPacketsList;
    }

    @Override
    public void sendPackets(List<Packet> packets) {
        for (Packet packet : packets)
            sendingPacketsList.addLast(new Pair<Packet,Integer>(packet, 0));
    }

    @Override
    public String getPacketsState() {
        String outString = new String();
        for (Pair<Packet, Integer> pair : sendingPacketsList) {
            Packet packet = pair.k;
            Integer sendingProgress = pair.v;
            outString += packet.getID().toString() + " " + sendingProgress.toString();
        }
        return outString;
    }
}
