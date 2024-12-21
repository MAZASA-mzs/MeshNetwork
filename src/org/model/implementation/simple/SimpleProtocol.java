package org.model.implementation.simple;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.model.abstracts.AbstractProtocol;
import org.model.implementation.simple.SimpleProtocolData;
import org.model.entity.Node;
import org.model.ids.PacketID;
import org.model.structures.Message;
import org.model.structures.Packet;

public class SimpleProtocol extends AbstractProtocol {
    private static int startTtl = 64; 
    private Node                  node;
    private Deque<PacketID>       packetQueue;
    private Map<PacketID, Packet> packets;

    public SimpleProtocol(Node node) {
        this.node = node;
        this.packetQueue = new LinkedList<PacketID>();
        this.packets = new HashMap<PacketID, Packet>();
    }

    @Override
    public void sendMessages(List<Message> messages) {
        for (Message message : messages) {
            int partsCount = message.getSize() / SimpleProtocolData.size;
            if ((message.getSize() % SimpleProtocolData.size) != 0) {
                partsCount++;
            }
            for (int i = 0; i < partsCount; i++) {
                SimpleProtocolData protocolData = new SimpleProtocolData(SimpleProtocol.startTtl, message.getDestination(), partsCount, i);
                int packetSize = 0;
                Packet packet = new Packet(this.node.getID(), message.getDestination(), SimpleProtocolData.size, protocolData);
                this.packets.put(packet.getID(), packet);
                this.packetQueue.addLast(packet.getID());
            }
        }
    }
}
