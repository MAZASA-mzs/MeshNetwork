package org.model.implementations.base.meshevents;

import org.controller.datacollection.MeshEvent;
import org.model.ids.ConnectionID;
import org.model.ids.PacketID;

public class PacketLost implements MeshEvent{
    private final ConnectionID connectionID;
    private final PacketID packetID;

    public PacketLost(ConnectionID connectionID, PacketID packetID) {
        this.connectionID = connectionID;
        this.packetID = packetID;
    }

    @Override
    public String getEventType() {
        return "PacketLost";
    }

    @Override
    public String getEventData() {
        return connectionID.toString() + " " + packetID.toString();
    }
}
