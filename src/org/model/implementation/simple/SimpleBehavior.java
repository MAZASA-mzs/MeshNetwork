package org.model.implementation.simple;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

import org.model.abstracts.AbstractBehavior;
import org.model.containers.NodeStorage;
import org.model.ids.NodeID;
import org.model.structures.Message;

public class SimpleBehavior extends AbstractBehavior {
    private final NodeID nodeID;
    private NodeID nextNodeID;
    private int nextMessageSize;
    private int maxMessageSize = 128;
    private int maxMessageTimer = 128;
    private int nextMessageTimer = 0;

    public SimpleBehavior(NodeID nodeID) {
        this.nodeID = nodeID;
    }

    @Override
    public void tick() {
        if (this.nextMessageTimer <= 0) {
            Random random = new Random();
            Object[] nodeArray = NodeStorage.getIDs().toArray();
            if (nodeArray == null || nodeArray.length == 0) {
                return;
            }
            nextMessageSize = random.nextInt(this.maxMessageSize);
            nextNodeID = (NodeID)nodeArray[random.nextInt(nodeArray.length)];
            this.nextMessageTimer = random.nextInt(maxMessageTimer);
            return;
        }
        this.nextMessageTimer -= 1;
    }

    @Override
    public List<Message> getNewMessages() {
        Message message = new Message(nextNodeID, nextMessageSize);
        LinkedList<Message> messages = new LinkedList<Message>();
        messages.addLast(message);
        return messages;
    }
}
