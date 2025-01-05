package org.model.implementations.simple;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

import org.model.containers.NodeStorage;
import org.model.ids.NodeID;
import org.model.interfaces.UserTrafficBehavior;
import org.model.structures.Message;

public class SimpleTrafficBehavior implements UserTrafficBehavior {
    private static Random random = new Random();
    private final NodeID nodeID;
    private NodeID nextNodeID;
    private int nextMessageSize;
    private int maxMessageSize = 128;
    private int maxMessageTimer = 128;
    private int nextMessageTimer = 0;
    private boolean hasNewMessage = true;

    public SimpleTrafficBehavior(NodeID nodeID) {
        this.nodeID = nodeID;
    }

    @Override
    public void tick() {
        if (nextMessageTimer <= 0) {
            Object[] nodeArray = NodeStorage.getIDs().toArray();
            if (nodeArray == null || nodeArray.length == 0) {
                return;
            }
            nextMessageSize = random.nextInt(maxMessageSize);
            nextNodeID = (NodeID)nodeArray[random.nextInt(nodeArray.length)];
            nextMessageTimer = random.nextInt(maxMessageTimer);
            hasNewMessage = true;
            return;
        }
        nextMessageTimer -= 1;
    }

    @Override
    public List<Message> getNewMessages() {
        LinkedList<Message> messages = new LinkedList<Message>();
        if (hasNewMessage) {
            Message message = new Message(nextNodeID, nextMessageSize);
            messages.addLast(message);
            hasNewMessage = false;
        }
        return messages;
    }

    @Override
    public String getType() {
        return "SimpleTrafficBehavior";
    }

    @Override
    public String getState() {
        return Boolean.toString(hasNewMessage) + " " + Integer.toString(nextMessageTimer) + " " + nodeID.toString();
    }
}
