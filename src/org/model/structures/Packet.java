package org.model.structures;

import org.model.ids.PacketID;
import org.model.ids.NodeID;
import org.model.abstracts.AbstractProtocolData;

/**
 * Represents a data packet being transmitted between nodes.
 */
public class Packet {

    private final PacketID  id;
    private final NodeID    destination;
    private final int       size;
    AbstractProtocolData    protocolData;

    public Packet(NodeID destination, int size, AbstractProtocolData protocolData) {
        this.id = new PacketID();
        this.destination = destination;
        this.size = size;
        this.protocolData = protocolData;
    }

    public PacketID getId() {
        return id;
    }

    public NodeID getDestination() {
        return destination;
    }

    public int getSize() {
        return size + protocolData.getSize();
    }

    public AbstractProtocolData getProtocolData() {
        return protocolData;
    }
}
