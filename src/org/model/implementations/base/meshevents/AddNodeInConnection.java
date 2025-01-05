package org.model.implementations.base.meshevents;

import org.controller.datacollection.MeshEvent;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;

public class AddNodeInConnection implements MeshEvent {
    private final ConnectionID connectionID;
    private final NodeID nodeID;

    public AddNodeInConnection(ConnectionID connectionID, NodeID nodeID) {
        this.connectionID = connectionID;
        this.nodeID = nodeID;
    }


    @Override
    public String getEventType() {
        return "AddNodeInConnection";
    }

    @Override
    public String getEventData() {
        return this.connectionID.toString() + " " + this.nodeID.toString();
    }
}
