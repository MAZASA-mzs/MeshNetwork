package org.abstracts;

import java.util.List;
import org.entities.Message;
import org.entities.Packet;
import org.ids.packet.PacketID;

/**
 * This abstract class defines the interface for implementing various routing algorithms and protocols
 * in a mesh network simulation. Concrete subclasses should implement the abstract methods
 * to define the behavior of message sending, receiving, and packet handling.
 */
public abstract class AbstractProtocol {
    
    /**
     * Simulates a single time unit in the mesh network simulation.
     */
    public abstract void tick();

    /**
     * Receive messages for processing. 
     *
     * @param messages A list of message objects to be processed
     */
    public abstract void sendMessages(List<Message> messages);

    /**
     * Receives a packet from the network and processes it according to the protocol.
     *
     * @param packet The packet received from the network
     */
    public abstract void receivePacket(Packet packet);

    /**
     * Checks the result of sending a packet and returns whether it was successful.
     *
     * @param packetID The ID of the packet being checked
     * @param isSuccessfulSending Whether the packet was sent successfully
     * @return true if the packet was sent successfully, false otherwise
     */
    public abstract void sendingPacketResult(PacketID packetID, boolean isSuccessfulSending);
}
