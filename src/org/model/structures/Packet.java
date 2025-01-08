package org.model.structures;

import org.model.ids.PacketID;
import org.model.interfaces.PacketProtocolData;
import org.model.ids.NodeID;

/**
 * Represents a data packet being transmitted between nodes.
 */
public class Packet {

    private final PacketID id;
    private final NodeID   lastSender;
    private final NodeID   closeDestination;
    private final int      size;
    PacketProtocolData     protocolData;

    public Packet(NodeID lastSender, NodeID destination, int size, PacketProtocolData protocolData) {
        this.id = new PacketID();
        this.lastSender = lastSender;
        this.closeDestination = destination;
        this.size = size;
        this.protocolData = protocolData;
    }

    public PacketID getID() {
        return id;
    }

    public NodeID getLastSender() {
        return lastSender;
    }

    public NodeID getCloseDestination() {
        return closeDestination;
    }

    public int getSize() {
        return size + protocolData.getSize();
    }

    public PacketProtocolData getProtocolData() {
        return protocolData;
    }
}
