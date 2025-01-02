package org.model.structures;

import org.model.ids.PacketID;
import org.model.interfaces.PacketProtocolData;
import org.model.ids.NodeID;

/**
 * Represents a data packet being transmitted between nodes.
 */
public class Packet {

    private final PacketID  id;
    private final NodeID    lastSender;
    private final NodeID    destination;
    private final int       size;
    private double          transferRate;
    PacketProtocolData    protocolData;

    public Packet(NodeID lastSender, NodeID destination, int size, PacketProtocolData protocolData) {
        this.id = new PacketID();
        this.lastSender = lastSender;
        this.destination = destination;
        this.size = size;
        this.transferRate = 0;
        this.protocolData = protocolData;
    }

    public PacketID getID() {
        return id;
    }

    public NodeID getLastSender() {
        return lastSender;
    }

    public NodeID getDestination() {
        return destination;
    }

    public int getSize() {
        return size + protocolData.getSize();
    }

    public double getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(double transferRate) {
        this.transferRate = transferRate; 
    }

    public PacketProtocolData getProtocolData() {
        return protocolData;
    }
}
