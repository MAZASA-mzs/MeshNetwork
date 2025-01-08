package org.model.implementations.simple;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

import org.model.containers.NodeStorage;
import org.model.entity.Node;
import org.model.ids.NodeID;
import org.model.interfaces.UserTrafficBehavior;
import org.model.structures.Message;

public class SimpleTrafficBehavior implements UserTrafficBehavior {
    private static Random random = new Random();
    private static boolean isMessageCreated;
    private final NodeID nodeID;
    private NodeID nextNodeID;
    private int nextMessageSize;
    private int maxMessageSize = 32;
    private int minMessageTimer = 16;
    private int maxMessageTimer = 48;
    private int nextMessageTimer = 0;
    private boolean hasNewMessage = true;

    public SimpleTrafficBehavior(NodeID nodeID) {
        this.nodeID = nodeID;
    }

    @Override
    public void tick() {
        Node node = NodeStorage.get(nodeID);
        boolean isNodeConnected = node.getConnectionsSet().isEmpty();
        if ((!isMessageCreated) && (nextMessageTimer <= 0) && (!isNodeConnected)) {
            Object[] nodeArray = NodeStorage.getIDs().toArray();
            if (nodeArray == null || nodeArray.length == 0) {
                return;
            }
            nextMessageSize = random.nextInt(maxMessageSize);
            nextNodeID = (NodeID)nodeArray[random.nextInt(nodeArray.length)];
            nextMessageTimer = random.nextInt(minMessageTimer, maxMessageTimer);
            hasNewMessage = true;
            isMessageCreated = true;
            return;
        }
        nextMessageTimer -= 1;
    }

    @Override
    public List<Message> getNewMessages() {
        LinkedList<Message> messages = new LinkedList<Message>();
        if (hasNewMessage) {
            Message message = new Message(this.nodeID, nextNodeID, nextMessageSize);
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
