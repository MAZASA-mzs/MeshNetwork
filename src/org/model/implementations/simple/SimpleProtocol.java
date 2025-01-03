package org.model.implementations.simple;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.controller.DataCollector;
import org.model.containers.ConnectionStorage;
import org.model.containers.NodeStorage;
import org.model.entity.Node;
import org.model.entity.Connection;
import org.model.ids.ConnectionID;
import org.model.ids.MessageID;
import org.model.ids.NodeID;
import org.model.ids.PacketID;
import org.model.interfaces.NetworkProtocol;
import org.model.structures.Message;
import org.model.structures.Packet;
import org.model.structures.Pair;


public class SimpleProtocol extends NetworkProtocol {
    private static int startTtl = 64; 
    private final NodeID          nodeID;
    private Deque<PacketID>       packetQueue;
    private Set<MessageID>        recivedMessages;
    private Map<PacketID, Packet> packetsInProcess;
    private Map<MessageID, Pair<Integer, Set<Integer>>> recivingMessages;

    public SimpleProtocol(NodeID nodeID) {
        this.nodeID = nodeID;

        this.packetQueue      = new LinkedList<>();
        this.packetsInProcess = new HashMap<>();
        this.recivedMessages  = new HashSet<>();
        this.recivingMessages = new HashMap<>();
    }

    @Override
    public void sendMessages(List<Message> messages) {
        Node thisNode = NodeStorage.get(this.nodeID);
        for (Message message : messages) {
            int current_msg_size = message.getSize();
            int partsCount = message.getSize() / SimpleProtocolData.size;
            if ((message.getSize() % SimpleProtocolData.size) > 0)
                partsCount += 1;
            int partNumber = 0;
            while (current_msg_size > 0) {
                partNumber += 1;
                int delta = Math.min(SimpleProtocolData.size, current_msg_size);
                current_msg_size -= delta;
                SimpleProtocolData data = new SimpleProtocolData(SimpleProtocol.startTtl, message.getID(),
                                                                thisNode.getID(), message.getDestination(),
                                                                partsCount, partNumber);
                Packet packet = new Packet(thisNode.getID(), message.getDestination(), delta, data);
                this.packetsInProcess.put(packet.getID(), packet);
                this.packetQueue.addLast(packet.getID());
            }
        }
    }

    @Override
    public void tick() {
        Node thisNode = NodeStorage.get(this.nodeID);
        for (ConnectionID connectionID : thisNode.getConnectionsSet()) {
            Connection connection = ConnectionStorage.get(connectionID);
            List<Packet> packets = this.packetQueue.stream()
                .map(this.packetsInProcess::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            connection.sendPackets(packets);
        }
        this.packetQueue.clear();
    }

    @Override
    public void receivePackets(List<Packet> packets) {
        for (Packet packet : packets) 
            this.receivePacket(packet);
        }

    public void receivePacket(Packet packet) {
        Node thisNode = NodeStorage.get(this.nodeID);
        SimpleProtocolData packetData = (SimpleProtocolData) packet.getProtocolData();
        // if this.node is receiver
        if (packet.getDestination().equals(thisNode.getID())) {
            MessageID messageID = packetData.getMessageID();
            // skip already recieved messages
            if (this.recivedMessages.contains(messageID)) return;
            if (!this.recivingMessages.containsKey(messageID)) {
                Pair<Integer, Set<Integer>> pair = new Pair<>(packetData.getPartsCount(), new HashSet<Integer>());
                this.recivingMessages.put(messageID, pair);
            }
            recivingMessages.get(messageID).v.add(packetData.getPartNumber());
            if (recivingMessages.get(messageID).k == recivingMessages.get(messageID).v.size()) {
                DataCollector.messageRecieved(thisNode.getID(), messageID);
                this.recivedMessages.add(messageID);
                this.recivingMessages.remove(messageID);
            }
        }
        // route packet
        else {
            // skip expired packets
            if (packetData.getTtl() <= 0) {
                DataCollector.packetDied(packet.getID());
                return;
            }
            SimpleProtocolData data = new SimpleProtocolData(packetData.getTtl()-1, packetData.getMessageID(),
                                                            packetData.getNodeSenderID(), packet.getDestination(),
                                                            packetData.getPartsCount(), packetData.getPartNumber());
            Packet newPacket = new Packet(thisNode.getID(), packet.getDestination(), packetData.getSize(), data);
            this.packetsInProcess.put(newPacket.getID(), newPacket);
            this.packetQueue.addLast(newPacket.getID());
        }
    }

    @Override
    public void sendingPacketResult(PacketID id, boolean isSuccessfulSending) {
        if (isSuccessfulSending) {
            this.packetsInProcess.remove(id);
        }
        else {
            this.packetQueue.addLast(id);
        }
    }
}
