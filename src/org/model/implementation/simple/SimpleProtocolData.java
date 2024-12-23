package org.model.implementation.simple;

import org.model.abstracts.AbstractProtocolData;
import org.model.ids.MessageID;
import org.model.ids.NodeID;

public class SimpleProtocolData extends AbstractProtocolData{

    public static int       size = 64;
    private       int       ttl;
    private final MessageID messageID;
    private final NodeID    nodeSenderID;
    private final NodeID    nodeReceiverID;
    private final int       partsCount;
    private final int       partNumber;


    public SimpleProtocolData(int ttl, MessageID messageID, NodeID nodeSenderID, NodeID nodeReceiverID, int partsCount, int partNumber) {
        this.ttl = ttl;
        this.messageID      = messageID;
        this.nodeSenderID   = nodeSenderID;
        this.nodeReceiverID = nodeReceiverID;
        this.partsCount     = partsCount;
        this.partNumber     = partNumber;
    }

    public int getSize() {
        return size;
    }

    public int getTtl() {
        return ttl;
    }

    // Setter for ttl that only decrements its value
    public void setTtl(int newTtl) {
        if (newTtl >= 0 && ttl > newTtl) {
            ttl = newTtl;
        } else {
            System.err.println("Invalid TTL value.");
        }
    }

    public MessageID getMessageID() {
        return messageID;
    }

    public NodeID getNodeSenderID() {
        return nodeSenderID;
    }

    public NodeID getNodeReceiverID() {
        return nodeReceiverID;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public int getPartNumber() {
        return partNumber;
    }

}