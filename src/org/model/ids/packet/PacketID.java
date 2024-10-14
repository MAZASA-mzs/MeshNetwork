package org.model.ids.packet;

import java.util.UUID;

import org.model.abstracts.AbstractID;

/**
 * Represents a unique identifier for a Packet.
 */
public class PacketID extends AbstractID {

    public PacketID() {
        super();
    }

    public PacketID(UUID id) {
        super(id);
    }
    
    @Override
    public PacketID copy() {
        return new PacketID(id);
    }
}
