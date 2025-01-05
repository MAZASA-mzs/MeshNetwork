package org.model.implementations.simple.meshevents;

import org.controller.datacollection.MeshEvent;
import org.model.ids.PacketID;

public class PacketDied implements MeshEvent{
    private final PacketID packetID;

    public PacketDied(PacketID packetID) {
        this.packetID = packetID;
    }

    @Override
    public String getEventType() {
        return "PacketDied";
    }

    @Override
    public String getEventData() {
        return packetID.toString();
    }
}
