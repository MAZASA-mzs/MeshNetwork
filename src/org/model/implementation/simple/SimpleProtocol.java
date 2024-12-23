package org.model.implementation.simple;

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
import org.model.abstracts.AbstractProtocol;
import org.model.containers.ConnectionStorage;
import org.model.entity.Node;
import org.model.entity.Connection;
import org.model.ids.ConnectionID;
import org.model.ids.MessageID;
import org.model.ids.PacketID;
import org.model.structures.Message;
import org.model.structures.Packet;
import org.model.structures.Pair;


public class SimpleProtocol extends AbstractProtocol {
    private static int startTtl = 64; 
    private Node                  node;
    private Deque<PacketID>       packetQueue;
    private Map<PacketID, Packet> packetsInProcess;
    private Set<MessageID>        recivedMessages;
    private Map<MessageID, Pair<Integer, Set<Integer>>> recivingMessages;

    public SimpleProtocol(Node node) {
        this.node = node;
        this.packetQueue      = new LinkedList<>();
        this.packetsInProcess = new HashMap<>();
        this.recivedMessages  = new HashSet<>();
        this.recivingMessages = new HashMap<>();
    }

    @Override
    public void sendMessages(List<Message> messages) {
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
                                                                this.node.getID(), message.getDestination(),
                                                                partsCount, partNumber);
                Packet packet = new Packet(this.node.getID(), message.getDestination(), delta, data);
                this.packetsInProcess.put(packet.getID(), packet);
                this.packetQueue.addLast(packet.getID());
            }
        }
    }

    @Override
    public void tick() {
        for (ConnectionID connectionID : node.getConnectionsList()) {
            Connection connection = ConnectionStorage.get(connectionID);
            List<Packet> packets = this.packetQueue.stream()
                .map(this.packetsInProcess::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            // System.out.println("Connection: " + connectionID.toString());
            // System.out.println(packets);
            connection.sendPackets(packets);
        }
        this.packetQueue.clear();
    }

    @Override
    public void receivePackets(List<Packet> packets) {
        for (Packet packet : packets) {
            SimpleProtocolData packetData = (SimpleProtocolData) packet.getProtocolData();
            // if node is receiver
            if (packet.getDestination().equals(this.node.getID())) {
                MessageID messageID = packetData.getMessageID();
                // skip already recieved messages
                if (this.recivedMessages.contains(messageID)) continue;
                if (!this.recivingMessages.containsKey(messageID)) {
                    Pair<Integer, Set<Integer>> pair = new Pair<>(packetData.getPartsCount(), new HashSet<Integer>());
                    this.recivingMessages.put(messageID, pair);
                }
                recivingMessages.get(messageID).v.add(packetData.getPartNumber());
                if (recivingMessages.get(messageID).k == recivingMessages.get(messageID).v.size()) {
                    DataCollector.messageRecieved(this.node.getID(), messageID);
                    this.recivedMessages.add(messageID);
                    this.recivingMessages.remove(messageID);
                }
            }
            // route packet
            else {
                // skip expired packets
                if (packetData.getTtl() <= 0) continue;
                SimpleProtocolData data = new SimpleProtocolData(packetData.getTtl()-1, packetData.getMessageID(),
                                                                packetData.getNodeSenderID(), packet.getDestination(),
                                                                packetData.getPartsCount(), packetData.getPartNumber());
                Packet newPacket = new Packet(this.node.getID(), packet.getDestination(), packetData.getSize(), data);
                this.packetsInProcess.put(newPacket.getID(), newPacket);
                this.packetQueue.addLast(newPacket.getID());
            }
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
