package abstract;

import entities.Packet;
import entities.Message;

/**
 * Abstract class representing the protocol used for message transmission.
 */
public abstract class AbstractProtocol {

    public abstract void sendMessage(Message message);

    public abstract Packet receivePacket();

    public abstract void sendingPacketResult(String packetId);
}
