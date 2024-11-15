package org.model.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.model.structures.Packet;
import org.model.ids.ConnectionID;

/**
 * Represents a connection between nodes in the network.
 */
public class Connection {

    private final ConnectionID  id;
    private final boolean       isPtP;
    private int                 speed;
    private double              reliability;
    private List<Packet>        sendingPacketsList;

    public Connection(boolean isPtP, int speed, double reliability) {
        this.id = new ConnectionID();
        this.isPtP = isPtP;
        this.speed = speed;
        this.reliability = reliability;
        this.sendingPacketsList = new ArrayList<Packet>();
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

    public List<Packet> getConnectionsList() {
        return Collections.unmodifiableList(this.sendingPacketsList);
    }

    public void tick() {
        
    }

    public void sendPackets(List<Packet> packets) {
        sendingPacketsList.addAll(packets);
    }
}
