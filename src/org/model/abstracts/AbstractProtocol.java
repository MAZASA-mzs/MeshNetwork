package org.model.abstracts;

import java.util.List;

import org.model.structures.Message;
import org.model.structures.Packet;
import org.model.ids.PacketID;

/**
 * Define the interface for implementing various routing algorithms of protocols. 
 */
public abstract class AbstractProtocol {
    
    /**
     * Update Protocol state.
     */
    public abstract void tick();

    /**
     * Receive messages for processing. 
     *
     * @param messages A list of message objects to be processed
     */
    public abstract void sendMessages(List<Message> messages);

    /**
     * Receive a packet from the connection and add it to processing queue.
     *
     * @param packet The packet received from the connection
     */
    public abstract void receivePacket(Packet packet);

    /**
     * Called by connection.
     * Indicates the result of sending a packet to sender node.
     *
     * @param packetID The ID of the packet sent
     * @param isSuccessfulSending Whether the packet was sent successfully
     */
    public abstract void sendingPacketResult(PacketID packetID, boolean isSuccessfulSending);
}
