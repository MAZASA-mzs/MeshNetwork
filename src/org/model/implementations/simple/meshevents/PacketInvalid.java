package org.model.implementations.simple.meshevents;

import org.controller.datacollection.MeshEvent;
import org.model.ids.PacketID;

public class PacketInvalid implements MeshEvent {
    private final PacketID packetID;
    private final String err;

    public PacketInvalid(PacketID packetID, String err) {
        this.packetID = packetID;
        this.err = err;
    }

    @Override
    public String getEventType() {
        return "PacketInvalid";
    }

    @Override
    public String getEventData() {
        return packetID.toString() + " " + err;
    }
}
