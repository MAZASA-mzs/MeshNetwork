package org.model.ids;

import java.util.UUID;

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
}
