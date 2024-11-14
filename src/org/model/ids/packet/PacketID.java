<<<<<<<< HEAD:src/org/model/ids/PacketID.java
package org.model.ids;
========
package org.model.ids.packet;
>>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5:src/org/model/ids/packet/PacketID.java

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
}
