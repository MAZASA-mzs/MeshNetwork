package org.model.implementations.base.meshevents;

import org.controller.datacollection.MeshEvent;
import org.model.ids.MessageID;
import org.model.ids.NodeID;

public class MessageRecieved implements MeshEvent {
    private final NodeID nodeID;
    private final MessageID messageID;

    public MessageRecieved (NodeID nodeID, MessageID messageID) {
        this.nodeID = nodeID;
        this.messageID = messageID;
    }

    @Override
    public String getEventType() {
        return "MessageRacieved";
}

    @Override
    public String getEventData() {
        return nodeID.toString() + " " + messageID.toString();
    }
}
